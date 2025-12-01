package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Interaction to find and click office action buttons (Modificar/Eliminar)
 * Locates the office by name and clicks the specified button
 */
public class ClickOfficeButton implements Interaction {

    private final String officeName;
    private final ButtonType buttonType;

    public enum ButtonType {
        MODIFY("Modificar"),
        DELETE("Trash"); // For the trash icon button
        
        private final String identifier;
        
        ButtonType(String identifier) {
            this.identifier = identifier;
        }
        
        public String getIdentifier() {
            return identifier;
        }
    }

    public ClickOfficeButton(String officeName, ButtonType buttonType) {
        this.officeName = officeName;
        this.buttonType = buttonType;
    }

    public static ClickOfficeButton forOffice(String officeName, ButtonType buttonType) {
        return Tasks.instrumented(ClickOfficeButton.class, officeName, buttonType);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        // Scroll to bottom to ensure office card is visible
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Find and click the button based on type
        String script;
        if (buttonType == ButtonType.DELETE) {
            script = 
                "var targetText = 'Consultorio " + officeName + "';" +
                "var h3 = Array.from(document.querySelectorAll('h3')).find(h => {" +
                "  var text = h.textContent.trim();" +
                "  return text === targetText || text.startsWith(targetText + ' ');" +
                "});" +
                "if (h3) {" +
                "  var buttonContainer = h3.closest('.flex.items-start');" +
                "  if (buttonContainer) {" +
                "    var deleteButton = Array.from(buttonContainer.querySelectorAll('button')).find(b => b.querySelector('svg.lucide-trash2'));" +
                "    if (deleteButton) {" +
                "      deleteButton.scrollIntoView({behavior: 'instant', block: 'center'});" +
                "      deleteButton.focus();" +
                "      var events = ['mouseenter', 'mouseover', 'mousedown', 'mouseup', 'click'];" +
                "      events.forEach(function(eventType) {" +
                "        var event = new MouseEvent(eventType, {" +
                "          bubbles: true," +
                "          cancelable: true," +
                "          view: window" +
                "        });" +
                "        deleteButton.dispatchEvent(event);" +
                "      });" +
                "      deleteButton.click();" +
                "      return true;" +
                "    }" +
                "  }" +
                "}" +
                "return false;";
        } else { // MODIFY
            script = 
                "var targetText = 'Consultorio " + officeName + "';" +
                "var h3 = Array.from(document.querySelectorAll('h3')).find(h => {" +
                "  var text = h.textContent.trim();" +
                "  return text === targetText || text.startsWith(targetText + ' ');" +
                "});" +
                "if (h3) {" +
                "  var buttonContainer = h3.closest('.flex.items-start');" +
                "  if (buttonContainer) {" +
                "    var modifyButton = Array.from(buttonContainer.querySelectorAll('button')).find(b => b.textContent.includes('Modificar'));" +
                "    if (modifyButton) {" +
                "      modifyButton.scrollIntoView({behavior: 'instant', block: 'center'});" +
                "      modifyButton.focus();" +
                "      var events = ['mouseenter', 'mouseover', 'mousedown', 'mouseup', 'click'];" +
                "      events.forEach(function(eventType) {" +
                "        var event = new MouseEvent(eventType, {" +
                "          bubbles: true," +
                "          cancelable: true," +
                "          view: window" +
                "        });" +
                "        modifyButton.dispatchEvent(event);" +
                "      });" +
                "      modifyButton.click();" +
                "      return true;" +
                "    }" +
                "  }" +
                "}" +
                "return false;";
        }
        
        js.executeScript(script);
    }
}