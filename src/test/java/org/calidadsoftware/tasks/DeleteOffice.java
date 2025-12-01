// Task to delete a medical office
package org.calidadsoftware.tasks;

import org.calidadsoftware.utils.WaitFor;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

public class DeleteOffice implements Task {

    private final String officeName;

    public DeleteOffice(String officeName) {
        this.officeName = officeName;
    }

    public static DeleteOffice named(String officeName) {
        return Tasks.instrumented(DeleteOffice.class, officeName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        actor.attemptsTo(WaitFor.sleep(2));

        // Find and click the Eliminar button using JavaScript
        String findAndClickScript = """
            const cards = document.querySelectorAll('[class*="border"][class*="rounded"]');
            console.log('Looking for office to delete: %s');
            console.log('Found cards:', cards.length);
            
            for (const card of cards) {
                // Try multiple selectors for the office name
                const nameElement = card.querySelector('h3') || 
                                   card.querySelector('div.text-lg.font-semibold') ||
                                   card.querySelector('[class*="font-semibold"]');
                
                if (nameElement) {
                    const text = nameElement.textContent.trim();
                    console.log('Found office:', text);
                    
                    // Match exact number (e.g., "Consultorio 109" should match "109")
                    if (text.includes('Consultorio %s') || text === '%s') {
                        console.log('Match found! Looking for Eliminar button');
                        const buttons = card.querySelectorAll('button');
                        console.log('Found buttons:', buttons.length);
                        
                        for (const btn of buttons) {
                            console.log('Button text:', btn.textContent);
                            if (btn.textContent.includes('Eliminar')) {
                                console.log('Clicking Eliminar button');
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
        System.out.println("Click Eliminar result: " + result);
        actor.attemptsTo(WaitFor.sleep(2));
        
        // Click Eliminar button in confirmation dialog
        String clickEliminarScript = """
            const buttons = document.querySelectorAll('[role="alertdialog"] button, [role="dialog"] button');
            for (const btn of buttons) {
                if (btn.textContent.trim() === 'Eliminar') {
                    btn.click();
                    return true;
                }
            }
            return false;
        """;
        js.executeScript(clickEliminarScript);
        actor.attemptsTo(WaitFor.sleep(3));
    }
}
