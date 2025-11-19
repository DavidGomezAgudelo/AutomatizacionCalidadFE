// interaccion para esperar que elementos sean visibles o contengan texto
package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class WaitFor implements Interaction {

    private final Target target;
    private final int seconds;
    private final String text;
    private final Mode mode;

    public enum Mode {VISIBLE, CONTAINS_TEXT}

    public WaitFor(Target target, int seconds, String text, Mode mode) {
        this.target = target;
        this.seconds = seconds;
        this.text = text;
        this.mode = mode;
    }

    public static WaitFor visible(Target target, int seconds) {
        return Tasks.instrumented(WaitFor.class, target, seconds, null, Mode.VISIBLE);
    }

    public static WaitFor containsText(Target target, String text, int seconds) {
        return Tasks.instrumented(WaitFor.class, target, seconds, text, Mode.CONTAINS_TEXT);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (mode == Mode.VISIBLE) {
            actor.attemptsTo(
                    WaitUntil.the(target, WebElementStateMatchers.isVisible()).forNoMoreThan(seconds).seconds()
            );
        } else {
            actor.attemptsTo(
                    WaitUntil.the(target, WebElementStateMatchers.containsText(text)).forNoMoreThan(seconds).seconds()
            );
        }
    }
}

