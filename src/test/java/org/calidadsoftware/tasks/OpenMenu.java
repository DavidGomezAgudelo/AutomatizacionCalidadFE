package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.InventoryPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para abrir el menu de la pagina de inventario
public class OpenMenu implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClickOn.target(InventoryPage.MENU_BUTTON),
                WaitFor.sleep(2)
                );
    }

    public static Performable now() {
        return Tasks.instrumented(OpenMenu.class);
    }
}
