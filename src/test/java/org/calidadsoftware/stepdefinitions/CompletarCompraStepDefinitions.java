package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.interfaces.CartPage;
import org.calidadsoftware.questions.ConfirmationMessageVisible;
import org.calidadsoftware.questions.ErrorMessageVisible;
import org.calidadsoftware.tasks.AddToCart;
import org.calidadsoftware.tasks.Checkout;
import org.calidadsoftware.tasks.CompletePurchase;
import org.calidadsoftware.tasks.ViewCart;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

// definiciones de pasos para completar la compra
public class CompletarCompraStepDefinitions {

    @Given("ha avanzado del proceso de checkout")
    public void ha_avanzado_checkout() {
        CommonStepDefinitions.actor.attemptsTo(AddToCart.products(1));
        CommonStepDefinitions.actor.attemptsTo(ViewCart.now());
        CommonStepDefinitions.actor.attemptsTo(Checkout.now());
        CommonStepDefinitions.actor.attemptsTo(CompletePurchase.withInfo("John", "Doe", "12345"));
    }

    @When("accede a la página de checkout")
    public void accede_checkout() {
        CommonStepDefinitions.actor.attemptsTo(AddToCart.products(1));
        CommonStepDefinitions.actor.attemptsTo(ViewCart.now());
        CommonStepDefinitions.actor.attemptsTo(
                WaitUntil.the(CartPage.CHECKOUT_BUTTON, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(5).seconds()
        );
        CommonStepDefinitions.actor.attemptsTo(Checkout.now());
    }

    @When("ingresa su informacion personal")
    public void ingresa_informacion_personal() {
        CommonStepDefinitions.actor.attemptsTo(CompletePurchase.withInfo("John", "Doe", "12345"));
    }

    @When("deja campos obligatorios vacíos")
    public void deja_campos_vacios() {
        CommonStepDefinitions.actor.attemptsTo(CompletePurchase.withInfo("", "", ""));
    }

    @When("confirma la compra")
    public void confirma_compra() {
        // incluido en CompletePurchase
    }

    @Then("se debe continuar al resumen del pedido")
    public void continua_resumen() {
        // verificar que esté en la página de resumen
    }

    @Then("se debe mostrar un mensaje de confirmacion")
    public void muestra_mensaje_confirmacion() {
        CommonStepDefinitions.actor.should(seeThat(ConfirmationMessageVisible.is(), is(true)));
    }

    @Then("se debe mostrar un mensaje de error")
    public void muestra_mensaje_error() {
        CommonStepDefinitions.actor.should(seeThat(ErrorMessageVisible.is(), is(true)));
    }
}
