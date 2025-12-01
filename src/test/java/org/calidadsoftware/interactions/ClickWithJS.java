package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * Interaction to click on elements using JavaScript with React event simulation
 */
public class ClickWithJS implements Interaction {

    private final Target target;

    public ClickWithJS(Target target) {
        this.target = target;
    }

    public static ClickWithJS on(Target target) {
        return Tasks.instrumented(ClickWithJS.class, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        WebElement element = target.resolveFor(actor);
        
        // Scroll element into view
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
        
        // Simulate complete user interaction sequence with React-compatible events
        js.executeScript(
            "var element = arguments[0];" +
            "element.focus();" +
            // Dispatch complete event sequence as a real user would trigger
            "var events = ['mouseenter', 'mouseover', 'mousedown', 'mouseup', 'click'];" +
            "events.forEach(function(eventType) {" +
            "  var event = new MouseEvent(eventType, {" +
            "    bubbles: true," +
            "    cancelable: true," +
            "    view: window" +
            "  });" +
            "  element.dispatchEvent(event);" +
            "});" +
            // Also trigger keyboard events for better compatibility
            "element.dispatchEvent(new KeyboardEvent('keydown', { key: 'Enter', code: 'Enter', keyCode: 13, bubbles: true }));" +
            "element.dispatchEvent(new KeyboardEvent('keypress', { key: 'Enter', code: 'Enter', keyCode: 13, bubbles: true }));" +
            "element.dispatchEvent(new KeyboardEvent('keyup', { key: 'Enter', code: 'Enter', keyCode: 13, bubbles: true }));",
            element
        );
    }
}
