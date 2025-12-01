package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * Interaction to enter text in React controlled inputs
 * Uses JavaScript to properly set the value and trigger input events
 */
public class EnterTextReact implements Interaction {

    private final String text;
    private final Target target;

    public EnterTextReact(String text, Target target) {
        this.text = text;
        this.target = target;
    }

    public static EnterTextReact valueInto(String text, Target target) {
        return Tasks.instrumented(EnterTextReact.class, text, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        WebElement element = target.resolveFor(actor);
        
        // Clear the field first, then set the new value using React-compatible setter
        js.executeScript(
            "var element = arguments[0];" +
            "var value = arguments[1];" +
            // First, clear the field
            "element.focus();" +
            "element.select();" +
            // Set the new value using React's native setter
            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeInputValueSetter.call(element, '');" +
            "element.dispatchEvent(new Event('input', { bubbles: true }));" +
            "nativeInputValueSetter.call(element, value);" +
            // Trigger all necessary events for React to detect the change
            "element.dispatchEvent(new Event('input', { bubbles: true }));" +
            "element.dispatchEvent(new Event('change', { bubbles: true }));" +
            "element.blur();",
            element, text
        );
    }
}
