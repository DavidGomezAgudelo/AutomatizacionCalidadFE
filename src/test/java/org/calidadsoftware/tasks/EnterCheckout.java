package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interfaces.CartPage;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.InventoryPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para acceder a checkout
public class EnterCheckout implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClickOn.target(CartPage.CHECKOUT_BUTTON),
                WaitFor.sleep(2)
        );
    }

    public static Performable now() {
        return Tasks.instrumented(EnterCheckout.class);
    }
}
