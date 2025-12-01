package org.calidadsoftware.tasks;

import java.util.Map;

import org.calidadsoftware.interactions.EnterTextReact;
import org.calidadsoftware.interactions.SelectReactOption;
import org.calidadsoftware.interactions.ClickWithJS;
import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.utils.WaitFor;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class CreateOffice implements Task {

    private final Map<String, String> officeData;

    public CreateOffice(Map<String, String> officeData) {
        this.officeData = officeData;
    }

    public static CreateOffice withData(Map<String, String> officeData) {
        return Tasks.instrumented(CreateOffice.class, officeData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitFor.visible(MedicalDashboardPage.OFFICE_NAME_INPUT, 10),
                EnterTextReact.valueInto(officeData.get("nombre"), MedicalDashboardPage.OFFICE_NAME_INPUT),
                WaitFor.sleep(1)
        );
        
        // Select especialidad using the new interaction
        String especialidad = officeData.get("especialidad");
        actor.attemptsTo(
                SelectReactOption.withValue(especialidad),
                WaitFor.sleep(1)
        );
        
        // Select sede using the new interaction
        String sede = officeData.get("sede");
        actor.attemptsTo(
                SelectReactOption.withValue(sede),
                WaitFor.sleep(1)
        );
        
        // Select estado using the new interaction
        String estado = officeData.get("estado");
        actor.attemptsTo(
                SelectReactOption.withValue(estado),
                WaitFor.sleep(2),
                ClickWithJS.on(MedicalDashboardPage.SAVE_OFFICE_BUTTON),
                WaitFor.sleep(3)
        );
    }
}
