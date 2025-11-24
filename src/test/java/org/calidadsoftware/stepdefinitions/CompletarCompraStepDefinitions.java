package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.questions.ConfirmationMessageVisible;
import org.calidadsoftware.questions.ErrorMessageVisible;
import org.calidadsoftware.questions.SummaryVisible;
import org.calidadsoftware.tasks.*;

import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class CompletarCompraStepDefinitions {

    @Given("el usuario ha realizado su compra hasta el checkout")
    public void el_usuario_realiza_su_compra() {
        // Llega hasta casi el final del checkout
        CommonStepDefinitions.actor.attemptsTo(
                CompleteCompra.now()
        );
    }

    @When("accede a la página de checkout")
    public void accede_checkout() {
        CommonStepDefinitions.actor.attemptsTo(
                EnterCheckout.now()
        );
    }

    @When("ingresa su informacion {string} {string} {string}")
    public void ingresa_informacion_personal(String nombre, String apellido, String codigoPostal) {
        CommonStepDefinitions.actor.attemptsTo(
                FillCheckout.withInfo(nombre, apellido, codigoPostal)
        );
    }

    @Then("se debe mostrar en compra {string}")
    public void el_sistema_debe_mostrar_en_compra(String resultado) {

        switch (resultado.toLowerCase()) {

            case "resumen":
                CommonStepDefinitions.actor.should(
                        seeThat(SummaryVisible.is(), is(true))
                );
                break;

            case "error":
                CommonStepDefinitions.actor.should(
                        seeThat(ErrorMessageVisible.is(), is(true))
                );
                break;

            case "confirmacion":
                CommonStepDefinitions.actor.should(
                        seeThat(ConfirmationMessageVisible.is(), is(true))
                );
                break;

            default:
                throw new IllegalArgumentException("Resultado no reconocido: " + resultado);
        }
    }
}
