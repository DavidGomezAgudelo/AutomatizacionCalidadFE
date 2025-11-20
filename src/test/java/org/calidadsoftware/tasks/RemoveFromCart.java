package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.CartPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para eliminar productos del carrito de compras
public class RemoveFromCart implements Task {

    private final int quantity;

    public RemoveFromCart(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (int i = 0; i < quantity; i++) {
            actor.attemptsTo(ClickOn.target(CartPage.REMOVE_BUTTON));
            actor.attemptsTo(WaitFor.sleep(2));
        }
    }

    public static Performable products(int quantity) {
        return Tasks.instrumented(RemoveFromCart.class, quantity);
    }
}
