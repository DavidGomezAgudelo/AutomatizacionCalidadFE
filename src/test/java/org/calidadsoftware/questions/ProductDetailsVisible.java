package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.ProductDetailPage;

import net.serenitybdd.screenplay.Question;

// pregunta para verificar si los detalles del producto son visibles
public class ProductDetailsVisible {

    // retorna true si el nombre del producto es visible en la pagina de detalle
    public static Question<Boolean> are() {
        return actor -> ProductDetailPage.PRODUCT_NAME.resolveFor(actor).isVisible();
    }
}

