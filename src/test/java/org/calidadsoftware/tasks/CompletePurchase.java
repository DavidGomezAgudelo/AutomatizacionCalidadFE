package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.interfaces.CheckoutPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para completar la compra ingresando los datos requeridos
public class CompletePurchase implements Task {

    private final String firstName;
    private final String lastName;
    private final String postalCode;

    public CompletePurchase(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                EnterText.valueInto(firstName, CheckoutPage.FIRST_NAME),
                EnterText.valueInto(lastName, CheckoutPage.LAST_NAME),
                EnterText.valueInto(postalCode, CheckoutPage.POSTAL_CODE),
                ClickOn.target(CheckoutPage.CONTINUE_BUTTON)
        );
        if (!(firstName.isEmpty() && lastName.isEmpty() && postalCode.isEmpty())) {
            actor.attemptsTo(ClickOn.target(CheckoutPage.FINISH_BUTTON));
        }
    }

    public static Performable withInfo(String firstName, String lastName, String postalCode) {
        return Tasks.instrumented(CompletePurchase.class, firstName, lastName, postalCode);
    }


}
