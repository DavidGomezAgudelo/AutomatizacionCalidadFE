package org.calidadsoftware.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

// pregunta para verificar si los productos estan ordenados segun un criterio
public class ProductsSorted implements Question<Boolean> {

    private final String criterion;

    private ProductsSorted(String criterion) {
        this.criterion = criterion;
    }

    public static ProductsSorted by(String criterion) {
        return new ProductsSorted(criterion);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // obtiene todos los nombres y precios de los productos en la pagina
        List<WebElement> nameElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".inventory_item_price"));

        if (nameElements.isEmpty()) {
            return false;
        }

        // extrae los nombres actuales de los productos
        List<String> actualNames = nameElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> expectedNames = new ArrayList<>(actualNames);

        // extrae y parsear los precios de los productos
        List<Double> actualPrices = new ArrayList<>();
        if (!priceElements.isEmpty()) {
            for (WebElement e : priceElements) {
                String text = e.getText().replaceAll("[^0-9.,]", "").replace(",", ".");
                try {
                    actualPrices.add(Double.parseDouble(text));
                } catch (NumberFormatException ex) {
                    actualPrices.add(Double.NaN);
                }
            }
        }

        // compara el orden actual con el orden esperado segun el criterio
        switch (criterion) {
            case "Name (A to Z)":
                Collections.sort(expectedNames);
                return actualNames.equals(expectedNames);

            case "Name (Z to A)":
                expectedNames.sort(Collections.reverseOrder());
                return actualNames.equals(expectedNames);

            case "Price (low to high)":
                if (actualPrices.isEmpty()) return false;
                List<Double> expectedPricesAsc = new ArrayList<>(actualPrices);
                expectedPricesAsc.sort(Comparator.naturalOrder());
                return actualPrices.equals(expectedPricesAsc);

            case "Price (high to low)":
                if (actualPrices.isEmpty()) return false;
                List<Double> expectedPricesDesc = new ArrayList<>(actualPrices);
                expectedPricesDesc.sort(Comparator.reverseOrder());
                return actualPrices.equals(expectedPricesDesc);

            default:
                // por defecto compara por nombre ascendente
                Collections.sort(expectedNames);
                return actualNames.equals(expectedNames);
        }
    }
}