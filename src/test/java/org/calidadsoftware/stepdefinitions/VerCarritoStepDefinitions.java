package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.CartPage;
import org.calidadsoftware.questions.CartItemsVisible;
import org.calidadsoftware.tasks.AddToCart;
import org.calidadsoftware.tasks.ViewCart;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

// definiciones de pasos para ver el carrito de compras
public class VerCarritoStepDefinitions {

    @Given("ha agregado productos al carrito")
    public void ha_agregado_productos() {
        CommonStepDefinitions.actor.attemptsTo(AddToCart.products(1));
    }

    @When("accede a la vista del carrito")
    public void accede_vista_carrito() {
        CommonStepDefinitions.actor.attemptsTo(ViewCart.now());
    }

    @Then("debe visualizar la lista de productos agregados")
    public void visualiza_lista_productos() {
        CommonStepDefinitions.actor.should(seeThat(CartItemsVisible.are(), is(true)));
    }
}
