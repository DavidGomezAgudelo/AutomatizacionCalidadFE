package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.drivers.DriverFactory;
import org.calidadsoftware.questions.LoginError;
import org.calidadsoftware.tasks.Login;
import org.calidadsoftware.tasks.OpenTheApplication;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

// definiciones de pasos para el login
public class LoginStepDefinitions {

    @Given("que el usuario está en la página de login")
    public void usuario_esta_en_pagina_login() {
        WebDriver browser = DriverFactory.firefox();
        CommonStepDefinitions.browser = browser;
        CommonStepDefinitions.actor.can(BrowseTheWeb.with(browser));
        CommonStepDefinitions.actor.attemptsTo(OpenTheApplication.on("https://www.saucedemo.com"));
    }

    @When("ingresa su usuario y contraseña correctos")
    public void usuario_ingresa_credenciales_correctas() {
        CommonStepDefinitions.actor.attemptsTo(Login.with("standard_user", "secret_sauce"));
    }


    @When("ingresa un usuario o contraseña incorrectos")
    public void usuario_ingresa_credenciales_incorrectos() {
        CommonStepDefinitions.actor.attemptsTo(Login.with("wrong", "wrong"));
    }

    @When("ingresa las credenciales de una cuenta bloqueada")
    public void usuario_credenciales_bloqueadas() {
        CommonStepDefinitions.actor.attemptsTo(Login.with("locked_out_user", "secret_sauce"));
    }

    @Then("el sistema lo redirige al catálogo de productos")
    public void redirige_catalogo() {
        assertTrue(CommonStepDefinitions.browser.getCurrentUrl().contains("inventory"));
    }

    @Then("el sistema muestra un mensaje de error")
    public void sistema_muestra_error() {
        assertTrue(LoginError.isVisible().answeredBy(CommonStepDefinitions.actor));
    }
}
