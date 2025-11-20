package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.tasks.DeleteProducts;
import org.calidadsoftware.questions.CartItemCount;
import org.calidadsoftware.questions.CartItemsVisible;
import org.calidadsoftware.tasks.AddToCart;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

// definiciones de pasos para eliminar productos del carrito
public class RemoveCarritoStepDefinitions {

    @Given("tiene productos dentro del carrito")
    public void tiene_productos_carrito() {
        CommonStepDefinitions.actor.attemptsTo(AddToCart.products(1));
    }

    @When("elimina productos")
    public void elimina_productos() {
        CommonStepDefinitions.actor.attemptsTo(
                DeleteProducts.now()
        );
    }

    @Then("se debe actualizar el contador del carrito")
    public void actualiza_contador() {
        CommonStepDefinitions.actor.should(seeThat(CartItemCount.value(), equalTo(0)));
    }

    @Then("los productos eliminados no deben aparecer en la lista del carrito")
    public void productos_no_aparecen() {
        CommonStepDefinitions.actor.should(seeThat(CartItemsVisible.are(), is(true)));
    }
}
