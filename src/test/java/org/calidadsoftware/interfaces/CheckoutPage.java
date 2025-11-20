// page object que mapea los elementos de la pagina de checkout
package org.calidadsoftware.interfaces;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage extends PageObject {

    public static final Target FIRST_NAME = Target.the("campo nombre")
            .located(By.id("first-name"));

    public static final Target LAST_NAME = Target.the("campo apellido")
            .located(By.id("last-name"));

    public static final Target POSTAL_CODE = Target.the("campo código postal")
            .located(By.id("postal-code"));

    public static final Target CONTINUE_BUTTON = Target.the("botón continuar")
            .located(By.id("continue"));

    public static final Target FINISH_BUTTON = Target.the("botón finalizar")
            .located(By.id("finish"));

    public static final Target CONFIRMATION_MESSAGE = Target.the("mensaje de confirmación")
            .located(By.className("complete-header"));

    public static final Target SUMMARY_CONTAINER = Target.the("contenedor del resumen de los articulos")
            .located(By.className("summary_info"));

    public static final Target ERROR_MESSAGE = Target.the("mensaje de error")
            .located(By.className("error-message-container"));
}
