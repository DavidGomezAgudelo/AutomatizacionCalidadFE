package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.CheckoutPage;

import net.serenitybdd.screenplay.Question;

public class SummaryVisible {

    // pregunta para verificar si la confirmacion del checkout es visible
    public static Question<Boolean> is() {
        return actor -> CheckoutPage.SUMMARY_CONTAINER.resolveFor(actor).isVisible();
    }
}
