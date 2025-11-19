package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.CheckoutPage;

import net.serenitybdd.screenplay.Question;

public class ErrorMessageVisible {

    // pregunta para verificar si el mensaje de error es visible
    public static Question<Boolean> is() {
        return actor -> CheckoutPage.ERROR_MESSAGE.resolveFor(actor).isVisible();
    }
}
