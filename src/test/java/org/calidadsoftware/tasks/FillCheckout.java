package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.interfaces.CartPage;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.CheckoutPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para completar la compra ingresando los datos requeridos
public class FillCheckout implements Task {

    private final String firstName;
    private final String lastName;
    private final String postalCode;

    public FillCheckout(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                EnterText.valueInto(firstName, CheckoutPage.FIRST_NAME),
                WaitFor.sleep(2),
                EnterText.valueInto(lastName, CheckoutPage.LAST_NAME),
                WaitFor.sleep(2),
                EnterText.valueInto(postalCode, CheckoutPage.POSTAL_CODE),
                WaitFor.sleep(2),
                ClickOn.target(CheckoutPage.CONTINUE_BUTTON),
                WaitFor.sleep(2)
        );
        // Intentar presionar FINISH si está visible
        if (CheckoutPage.FINISH_BUTTON.resolveFor(actor).isVisible()) {
            actor.attemptsTo(
                ClickOn.target(CheckoutPage.FINISH_BUTTON),
                WaitFor.sleep(2)
            );
        }
    }

    public static Performable withInfo(String firstName, String lastName, String postalCode) {
        return Tasks.instrumented(FillCheckout.class, firstName, lastName, postalCode);
    }


}
