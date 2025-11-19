package org.calidadsoftware.interfaces;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

// page object que mapea los elementos de la pagina de login
public class LoginPage extends PageObject {

    public static final Target USERNAME = Target.the("campo usuario")
            .located(By.id("user-name"));

    public static final Target PASSWORD = Target.the("campo contraseña")
            .located(By.id("password"));

    public static final Target LOGIN_BUTTON = Target.the("botón login")
            .located(By.id("login-button"));

    public static final Target ERROR_MESSAGE = Target.the("mensaje de error")
            .located(By.cssSelector("[data-test='error']"));
}

