package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.InventoryPage;

import net.serenitybdd.screenplay.Question;

public class ProductListVisible {

    // pregunta para verificar si la lista de productos es visible
    public static Question<Boolean> is() {
        return actor -> InventoryPage.PRODUCT_LIST.resolveFor(actor).isVisible();
    }
}
