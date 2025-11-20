package org.calidadsoftware.stepdefinitions;

import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.calidadsoftware.drivers.DriverFactory;
import org.calidadsoftware.tasks.Login;
import org.calidadsoftware.utils.OpenTheApplication;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// step definitions comunes compartidos entre multiples features
public class CommonStepDefinitions {

    public static WebDriver browser;
    public static Actor actor = Actor.named("usuario");

    // inicializa el navegador y configura el actor, sin login
    public static void openApplication() {
        browser = DriverFactory.firefox();
        actor.can(BrowseTheWeb.with(browser));
        String appUrl = getAppUrl();
        actor.attemptsTo(OpenTheApplication.on(appUrl));
    }

    // inicializa el navegador, configura el actor y realiza login
    @Given("que el usuario ha iniciado sesion")
    public void usuario_ha_iniciado_sesion() {
        openApplication();
        actor.attemptsTo(Login.with("standard_user", "secret_sauce"));
    }

    private static String getAppUrl() {
        Properties props = new Properties();
        try (InputStream input = CommonStepDefinitions.class.getClassLoader().getResourceAsStream("serenity.properties")) {
            if (input != null) {
                props.load(input);
                return props.getProperty("app.url");
            }
        } catch (IOException e) {
            // error
        }
        return "blank";
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
