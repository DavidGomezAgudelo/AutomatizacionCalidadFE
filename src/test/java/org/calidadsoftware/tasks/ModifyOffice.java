package org.calidadsoftware.tasks;

import java.util.Map;

import org.calidadsoftware.interactions.ClickOfficeButton;
import org.calidadsoftware.interactions.ClickConfirmButton;
import org.calidadsoftware.interactions.EnterTextReact;
import org.calidadsoftware.interactions.SelectReactOption;
import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.utils.WaitFor;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

public class ModifyOffice implements Task {

    private final String officeName;
    private final Map<String, String> updatedData;

    public ModifyOffice(String officeName, Map<String, String> updatedData) {
        this.officeName = officeName;
        this.updatedData = updatedData;
    }

    public static ModifyOffice named(String officeName, Map<String, String> updatedData) {
        return Tasks.instrumented(ModifyOffice.class, officeName, updatedData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        actor.attemptsTo(WaitFor.sleep(2));

        // Find and click the Modificar button using JavaScript
        String findAndClickScript = """
            const cards = document.querySelectorAll('[class*="border"][class*="rounded"]');
            console.log('Looking for office: %s');
            console.log('Found cards:', cards.length);
            
            for (const card of cards) {
                // Try multiple selectors for the office name
                const nameElement = card.querySelector('h3') || 
                                   card.querySelector('div.text-lg.font-semibold') ||
                                   card.querySelector('[class*="font-semibold"]');
                
                if (nameElement) {
                    const text = nameElement.textContent.trim();
                    console.log('Found office:', text);
                    
                    // Match exact number (e.g., "Consultorio 108" should match "108")
                    if (text.includes('Consultorio %s') || text === '%s') {
                        console.log('Match found! Looking for Modificar button');
                        const buttons = card.querySelectorAll('button');
                        console.log('Found buttons:', buttons.length);
                        
                        for (const btn of buttons) {
                            console.log('Button text:', btn.textContent);
                            if (btn.textContent.includes('Modificar')) {
                                console.log('Clicking Modificar button');
                                btn.scrollIntoView({ behavior: 'smooth', block: 'center' });
                                btn.click();
                                return true;
                            }
                        }
                    }
                }
            }
            console.log('Office not found');
            return false;
        """.formatted(officeName, officeName, officeName);
        
        Object result = js.executeScript(findAndClickScript);
        System.out.println("Click Modificar result: " + result);
        actor.attemptsTo(WaitFor.sleep(3));

        // Update number field if provided
        if (updatedData.containsKey("nombre")) {
            String newNumber = updatedData.get("nombre");
            String updateNumberScript = """
                const input = document.querySelector('input[id="number"]');
                if (input) {
                    const nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;
                    nativeInputValueSetter.call(input, '%s');
                    input.dispatchEvent(new Event('input', { bubbles: true }));
                    input.dispatchEvent(new Event('change', { bubbles: true }));
                }
            """.formatted(newNumber);
            js.executeScript(updateNumberScript);
            actor.attemptsTo(WaitFor.sleep(1));
        }

        // Update especialidad (Tipo) if provided
        if (updatedData.containsKey("especialidad")) {
            String especialidad = updatedData.get("especialidad");
            
            // Click the Tipo button to open dropdown
            String clickTipoScript = """
                const tipoButton = document.querySelector('button[id="type"]');
                if (tipoButton) {
                    tipoButton.click();
                }
            """;
            js.executeScript(clickTipoScript);
            actor.attemptsTo(WaitFor.sleep(1));
            
            // Select the option
            String selectTipoScript = """
                const items = document.querySelectorAll('[role="option"]');
                for (const item of items) {
                    if (item.textContent.includes('%s')) {
                        item.click();
                        return true;
                    }
                }
                return false;
            """.formatted(especialidad);
            js.executeScript(selectTipoScript);
            actor.attemptsTo(WaitFor.sleep(1));
        }

        // Update sede if provided
        if (updatedData.containsKey("sede")) {
            String sede = updatedData.get("sede");
            
            // Click the Sede button to open dropdown
            String clickSedeScript = """
                const sedeButton = document.querySelector('button[id="location"]');
                if (sedeButton) {
                    sedeButton.click();
                }
            """;
            js.executeScript(clickSedeScript);
            actor.attemptsTo(WaitFor.sleep(1));
            
            // Select the option
            String selectSedeScript = """
                const items = document.querySelectorAll('[role="option"]');
                for (const item of items) {
                    if (item.textContent.includes('%s')) {
                        item.click();
                        return true;
                    }
                }
                return false;
            """.formatted(sede);
            js.executeScript(selectSedeScript);
            actor.attemptsTo(WaitFor.sleep(1));
        }

        // Click Guardar button
        String clickGuardarScript = """
            const buttons = document.querySelectorAll('button');
            for (const btn of buttons) {
                if (btn.textContent.trim() === 'Guardar') {
                    btn.click();
                    return true;
                }
            }
            return false;
        """;
        js.executeScript(clickGuardarScript);
        actor.attemptsTo(WaitFor.sleep(3));
    }
}
