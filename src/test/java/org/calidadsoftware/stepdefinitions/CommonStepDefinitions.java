package org.calidadsoftware.stepdefinitions;

import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.calidadsoftware.drivers.DriverFactory;
import org.calidadsoftware.tasks.Login;
import org.calidadsoftware.tasks.OpenTheApplication;
import org.openqa.selenium.WebDriver;

// step definitions comunes compartidos entre multiples features
public class CommonStepDefinitions {

    public static WebDriver browser;
    public static Actor actor = Actor.named("usuario");

    // inicializa el navegador, configura el actor y realiza login
    @Given("que el usuario ha iniciado sesion")
    public void usuario_ha_iniciado_sesion() {
        browser = DriverFactory.firefox();
        actor.can(BrowseTheWeb.with(browser)); // le da al actor la habilidad de navegar
        actor.attemptsTo(OpenTheApplication.on("https://www.saucedemo.com"));
        actor.attemptsTo(Login.with("standard_user", "secret_sauce"));
    }

    // despues del login el usuario ya esta en el catalogo
    @Given("se encuentra en el catálogo de productos")
    public void se_encuentra_en_catalogo() {
        // ya está despues de login
    }

    public static Actor getActor() {
        return actor;
    }

    public static WebDriver getBrowser() {
        return browser;
    }
}
