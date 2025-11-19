package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.questions.RedirectedToLogin;
import org.calidadsoftware.tasks.Logout;
import org.calidadsoftware.tasks.OpenMenu;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

// definiciones de pasos para cerrar sesion
public class LogoutStepDefinitions {

    @When("abre el menu")
    public void abre_el_menu() {
        CommonStepDefinitions.actor.attemptsTo(OpenMenu.now());
    }

    @When("cierra sesion")
    public void cierra_sesion() {
        CommonStepDefinitions.actor.attemptsTo(Logout.now());
    }

    @Then("debe ser redirigirlo al login")
    public void redirigido_login() {
        CommonStepDefinitions.actor.should(seeThat(RedirectedToLogin.is(), is(true)));
    }
}
