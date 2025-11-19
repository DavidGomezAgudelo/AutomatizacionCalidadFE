package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.CartPage;

import net.serenitybdd.screenplay.Question;

public class CartItemsVisible {

    // pregunta para verificar si los items del carrito son visibles
    public static Question<Boolean> are() {
        return actor -> CartPage.CART_LIST.resolveFor(actor).isVisible();
    }
}
