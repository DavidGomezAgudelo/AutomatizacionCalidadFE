package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

// interaccion personalizada para realizar clicks en elementos de la pagina
public class ClickOn implements Interaction {

    private final Target target;

    public ClickOn(Target target) {
        this.target = target;
    }

    // metodo estatico para crear una instancia de clickon
    public static ClickOn target(Target target) {
        return Tasks.instrumented(ClickOn.class, target);
    }

    // ejecuta la accion de click sobre el target especificado
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(target));
    }
}


