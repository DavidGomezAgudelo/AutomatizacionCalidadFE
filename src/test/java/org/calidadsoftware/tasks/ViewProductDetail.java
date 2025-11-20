package org.calidadsoftware.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.InventoryPage;
import org.calidadsoftware.interactions.ClickOn;

// tarea para visualizar el detalle de un producto
public class ViewProductDetail implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        // hace click en el nombre del primer producto para ver su detalle
        actor.attemptsTo(ClickOn.target(InventoryPage.PRODUCT_NAME));
        actor.attemptsTo(WaitFor.sleep(2));
    }

    public static Performable ofFirstProduct() {
        return Tasks.instrumented(ViewProductDetail.class);
    }
}

