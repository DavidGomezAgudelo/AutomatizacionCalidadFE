package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.LoginPage;

import net.serenitybdd.screenplay.Question;

public class LoginError {

    // pregunta para verificar si hay error en el login
    public static Question<Boolean> isVisible() {
        return actor -> LoginPage.ERROR_MESSAGE.resolveFor(actor).isVisible();
    }
}

