package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Interaction to click confirmation buttons in dialogs/alert dialogs
 * Handles buttons like "Guardar" and "Eliminar" in modal dialogs
 */
public class ClickConfirmButton implements Interaction {

    private final ButtonType buttonType;
    private final DialogType dialogType;

    public enum ButtonType {
        SAVE("Guardar"),
        DELETE("Eliminar"),
        CANCEL("Cancelar");

        private final String text;

        ButtonType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public enum DialogType {
        DIALOG("dialog"), // For edit/create modals
        ALERT_DIALOG("alertdialog"); // For delete confirmations

        private final String role;

        DialogType(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    public ClickConfirmButton(ButtonType buttonType, DialogType dialogType) {
        this.buttonType = buttonType;
        this.dialogType = dialogType;
    }

    public static ClickConfirmButton withText(ButtonType buttonType, DialogType dialogType) {
        return Tasks.instrumented(ClickConfirmButton.class, buttonType, dialogType);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        // Small delay to ensure dialog is fully rendered
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Build the script to find and click the button with proper React event
        // handling
        String script = "var dialog = document.querySelector('[role=\"" + dialogType.getRole() + "\"]');" +
                "if (dialog) {" +
                "  var button = Array.from(dialog.querySelectorAll('button')).find(b => b.textContent.trim() === '"
                + buttonType.getText() + "');" +
                "  if (button) {" +
                "    button.scrollIntoView({behavior: 'instant', block: 'center'});" +
                "    button.focus();" +
                // Remove disabled attribute if present
                "    button.removeAttribute('disabled');" +
                // Get React fiber to trigger React events properly
                "    var reactKey = Object.keys(button).find(key => key.startsWith('__reactProps') || key.startsWith('__reactEventHandlers'));"
                +
                "    if (reactKey && button[reactKey] && button[reactKey].onClick) {" +
                "      try {" +
                "        button[reactKey].onClick({ preventDefault: function(){}, stopPropagation: function(){}, target: button, currentTarget: button });"
                +
                "      } catch(e) { console.log('React onClick error:', e); }" +
                "    }" +
                // Dispatch native events with proper bubbling
                "    var mouseDownEvent = new MouseEvent('mousedown', { bubbles: true, cancelable: true, view: window, detail: 1 });"
                +
                "    var mouseUpEvent = new MouseEvent('mouseup', { bubbles: true, cancelable: true, view: window, detail: 1 });"
                +
                "    var clickEvent = new MouseEvent('click', { bubbles: true, cancelable: true, view: window, detail: 1 });"
                +
                "    button.dispatchEvent(mouseDownEvent);" +
                "    button.dispatchEvent(mouseUpEvent);" +
                "    button.dispatchEvent(clickEvent);" +
                // Also use native click
                "    button.click();" +
                // If button is in a form, try to submit it
                "    var form = button.closest('form');" +
                "    if (form && button.type !== 'button') {" +
                "      var submitEvent = new Event('submit', { bubbles: true, cancelable: true });" +
                "      form.dispatchEvent(submitEvent);" +
                "    }" +
                "    return true;" +
                "  }" +
                "}" +
                "return false;";

        Boolean result = (Boolean) js.executeScript(script);

        // Additional delay to allow React to process
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
