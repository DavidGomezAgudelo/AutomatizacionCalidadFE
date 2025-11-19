package org.calidadsoftware.interfaces;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

// page object que mapea los elementos de la pagina de detalle de producto
public class ProductDetailPage extends PageObject {

    public static final Target PRODUCT_NAME = Target.the("nombre del producto")
            .located(By.className("inventory_details_name"));

    public static final Target PRODUCT_DESCRIPTION = Target.the("descripción del producto")
            .located(By.className("inventory_details_desc"));

    public static final Target PRODUCT_PRICE = Target.the("precio del producto")
            .located(By.className("inventory_details_price"));

    public static final Target ADD_TO_CART_BUTTON = Target.the("botón agregar al carrito")
            .located(By.cssSelector(".btn_inventory"));

    public static final Target BACK_BUTTON = Target.the("botón volver")
            .located(By.id("back-to-products"));
}

