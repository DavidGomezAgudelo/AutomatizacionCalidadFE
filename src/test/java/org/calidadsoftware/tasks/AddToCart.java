package org.calidadsoftware.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.calidadsoftware.interfaces.InventoryPage;
import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.utils.WaitFor;

// tarea para agregar productos al carrito de compras
public class AddToCart implements Task {

    private final int quantity;

    public AddToCart(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // agrega el primer producto si la cantidad es mayor o igual a 1
        if (quantity >= 1) {
            actor.attemptsTo(ClickOn.target(InventoryPage.ADD_TO_CART_FIRST));
            actor.attemptsTo(WaitFor.sleep(2));
        }
    }

    public static Performable products(int quantity) {
        return Tasks.instrumented(AddToCart.class, quantity);
    }
}

