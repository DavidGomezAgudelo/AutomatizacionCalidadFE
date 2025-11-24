package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.questions.LoginError;
import org.calidadsoftware.tasks.Login;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginStepDefinitions {

    @Given("que el usuario está en la página de login")
    public void usuario_esta_en_pagina_login() {
        CommonStepDefinitions.openApplication();
    }

    @When("ingresa su usuario {string} y contraseña {string}")
    public void usuario_ingresa_credenciales(String usuario, String contrasena) {
        CommonStepDefinitions.actor.attemptsTo(Login.with(usuario, contrasena));
    }

    @Then("se debe mostrar en login {string}")
    public void el_sistema_debe_mostrar_en_login(String resultado) {

        switch (resultado.toLowerCase()) {
            case "success":
                assertTrue(
                        "no se redirigió al inventario",
                        CommonStepDefinitions.browser.getCurrentUrl().contains("inventory"));
                break;

            case "error":
                assertTrue(
                        "El mensaje de error no se está mostrando",
                        LoginError.isVisible().answeredBy(CommonStepDefinitions.actor));
                break;

            default:
                throw new IllegalArgumentException("Resultado no reconocido: " + resultado);
        }
    }
}
