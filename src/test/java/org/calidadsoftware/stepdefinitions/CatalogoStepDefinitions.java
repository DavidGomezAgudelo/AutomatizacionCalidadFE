package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.questions.ProductListVisible;
import org.calidadsoftware.questions.ProductsSorted;
import org.calidadsoftware.tasks.SortProducts;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

// definiciones de pasos para visualizar el catalogo de productos
public class CatalogoStepDefinitions {

    @When("ordena productos")
    public void ordena_productos() {
        CommonStepDefinitions.actor.attemptsTo(SortProducts.by("Name (A to Z)")); // Asumir orden alfabético
    }

    @When("accede al catalogo de productos")
    public void accede_catalogo_productos() {
        // ya está despues de login
    }

    @Then("debe visualizar la lista de productos de la tienda")
    public void visualiza_lista_productos() {
        CommonStepDefinitions.actor.should(seeThat(ProductListVisible.is(), is(true)));
    }

    @Then("debe visualizar los productos ordenados")
    public void muestra_productos_ordenados() {
        CommonStepDefinitions.actor.should(seeThat(ProductsSorted.by("Name (A to Z)"), is(true)));
    }
}
