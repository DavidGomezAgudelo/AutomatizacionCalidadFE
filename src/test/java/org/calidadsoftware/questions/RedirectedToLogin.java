package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.LoginPage;

import net.serenitybdd.screenplay.Question;

public class RedirectedToLogin {

    // pregunta para verificar si se redirigio a la pagina de login
    public static Question<Boolean> is() {
        return actor -> LoginPage.LOGIN_BUTTON.resolveFor(actor).isVisible();
    }
}
