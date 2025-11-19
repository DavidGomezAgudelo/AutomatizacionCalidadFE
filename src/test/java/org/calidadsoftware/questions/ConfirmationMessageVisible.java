package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.CheckoutPage;

import net.serenitybdd.screenplay.Question;

public class ConfirmationMessageVisible {

    // pregunta para verificar si el mensaje de confirmacion es visible
    public static Question<Boolean> is() {
        return actor -> CheckoutPage.CONFIRMATION_MESSAGE.resolveFor(actor).isVisible();
    }
}
