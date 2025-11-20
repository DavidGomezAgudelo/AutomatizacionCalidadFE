package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.SelectOption;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.InventoryPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para ordenar los productos en la pagina de inventario
public class SortProducts implements Task {

    private final String sortOption;

    public SortProducts(String sortOption) {
        this.sortOption = sortOption;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SelectOption.byVisibleText(sortOption, InventoryPage.SORT_DROPDOWN));
        actor.attemptsTo(WaitFor.sleep(2));
    }

    public static Performable by(String sortOption) {
        return Tasks.instrumented(SortProducts.class, sortOption);
    }
}
