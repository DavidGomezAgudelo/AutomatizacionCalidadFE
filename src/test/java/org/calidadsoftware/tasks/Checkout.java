package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interfaces.CartPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para iniciar el proceso de checkout desde el carrito
public class Checkout implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickOn.target(CartPage.CHECKOUT_BUTTON));
    }

    public static Performable now() {
        return Tasks.instrumented(Checkout.class);
    }
}
