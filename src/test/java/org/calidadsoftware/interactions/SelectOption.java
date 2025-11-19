// interaccion para seleccionar opciones en dropdowns
package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.targets.Target;

public class SelectOption implements Interaction {

    private final String visibleText;
    private final Target target;

    public SelectOption(String visibleText, Target target) {
        this.visibleText = visibleText;
        this.target = target;
    }

    public static SelectOption byVisibleText(String visibleText, Target target) {
        return Tasks.instrumented(SelectOption.class, visibleText, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SelectFromOptions.byVisibleText(visibleText).from(target));
    }
}

