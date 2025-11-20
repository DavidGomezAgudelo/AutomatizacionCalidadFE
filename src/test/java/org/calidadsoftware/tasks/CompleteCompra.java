package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.stepdefinitions.CommonStepDefinitions;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.CheckoutPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para completar la compra
public class CompleteCompra implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        CommonStepDefinitions.actor.attemptsTo(
                AddToCart.products(1),
                ViewCart.now()
        );
    }

    public static Performable now() {
        return Tasks.instrumented(CompleteCompra.class);
    }
}
