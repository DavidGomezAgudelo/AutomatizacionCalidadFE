package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.interfaces.CartPage;
import org.calidadsoftware.stepdefinitions.CommonStepDefinitions;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.CheckoutPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para completar la eliminacion de productos
public class DeleteProducts implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        CommonStepDefinitions.actor.attemptsTo(
                ViewCart.now(),
                WaitFor.visible(CartPage.CART_LIST, 5),
                RemoveFromCart.products(1),
                WaitFor.sleep(2)
        );
    }

    public static Performable now() {
        return Tasks.instrumented(DeleteProducts.class);
    }
}
