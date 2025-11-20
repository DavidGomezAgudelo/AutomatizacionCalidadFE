package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.InventoryPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para cerrar sesion en la aplicacion
public class Logout implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitFor.visible(InventoryPage.LOGOUT_LINK, 10),
                WaitFor.sleep(2),
                ClickOn.target(InventoryPage.LOGOUT_LINK),
                WaitFor.sleep(2)
                );
    }

    public static Performable now() {
        return Tasks.instrumented(Logout.class);
    }
}
