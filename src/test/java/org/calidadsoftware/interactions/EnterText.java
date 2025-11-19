// interaccion para ingresar texto en campos de formulario
package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

public class EnterText implements Interaction {

    private final String value;
    private final Target target;

    public EnterText(String value, Target target) {
        this.value = value;
        this.target = target;
    }

    public static EnterText valueInto(String value, Target target) {
        return Tasks.instrumented(EnterText.class, value, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(value).into(target));
    }
}

