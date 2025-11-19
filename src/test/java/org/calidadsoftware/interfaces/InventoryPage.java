package org.calidadsoftware.interfaces;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

// page object que mapea los elementos de la pagina de inventario/catalogo
public class InventoryPage extends PageObject {

    public static final Target PRODUCT_LIST = Target.the("lista de productos")
            .located(By.className("inventory_list"));

    public static final Target PRODUCT_ITEM = Target.the("ítem de producto")
            .located(By.className("inventory_item"));

    public static final Target ADD_TO_CART_BUTTON = Target.the("botón agregar al carrito")
            .located(By.cssSelector(".btn_inventory"));

    public static final Target CART_BADGE = Target.the("contador del carrito")
            .located(By.className("shopping_cart_badge"));

    public static final Target CART_LINK = Target.the("link del carrito")
            .located(By.className("shopping_cart_link"));

    public static final Target PRODUCT_NAME = Target.the("nombre del producto")
            .located(By.className("inventory_item_name"));

    public static final Target SORT_DROPDOWN = Target.the("desplegable de ordenamiento")
            .located(By.className("product_sort_container"));

    public static final Target MENU_BUTTON = Target.the("botón menú")
            .located(By.id("react-burger-menu-btn"));

    public static final Target LOGOUT_LINK = Target.the("enlace logout")
            .located(By.id("logout_sidebar_link"));

    // botones especificos para agregar productos individuales al carrito
    public static final Target ADD_TO_CART_FIRST = Target.the("botón agregar al carrito primero")
            .located(By.id("add-to-cart-sauce-labs-backpack"));

    public static final Target ADD_TO_CART_SECOND = Target.the("botón agregar al carrito segundo")
            .located(By.id("add-to-cart-sauce-labs-bike-light"));

    public static final Target PRODUCT_PRICE = Target.the("precio del producto")
            .located(By.className("inventory_item_price"));
}

