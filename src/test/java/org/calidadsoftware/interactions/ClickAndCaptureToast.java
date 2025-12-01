package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * Custom interaction to click an element and immediately capture toast message
 * This is necessary because toasts can disappear quickly
 */
public class ClickAndCaptureToast implements Interaction {

    private final Target target;
    private String capturedToast = "";

    private ClickAndCaptureToast(Target target) {
        this.target = target;
    }

    public static ClickAndCaptureToast on(Target target) {
        return new ClickAndCaptureToast(target);
    }

    public String getCapturedToast() {
        return capturedToast;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement element = target.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        // Dispatch all events
        js.executeScript(
            "var element = arguments[0];" +
            "element.dispatchEvent(new MouseEvent('mouseenter', { bubbles: true }));" +
            "element.dispatchEvent(new MouseEvent('mouseover', { bubbles: true }));" +
            "element.dispatchEvent(new MouseEvent('mousedown', { bubbles: true }));" +
            "element.dispatchEvent(new MouseEvent('mouseup', { bubbles: true }));" +
            "element.dispatchEvent(new MouseEvent('click', { bubbles: true }));" +
            "element.dispatchEvent(new KeyboardEvent('keydown', { key: 'Enter', keyCode: 13, bubbles: true }));" +
            "element.dispatchEvent(new KeyboardEvent('keypress', { key: 'Enter', keyCode: 13, bubbles: true }));" +
            "element.dispatchEvent(new KeyboardEvent('keyup', { key: 'Enter', keyCode: 13, bubbles: true }));",
            element
        );

        // Also click directly
        element.click();

        // Wait a bit for toast to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Immediately capture toast
        String script = """
            let toast = document.querySelector('[data-sonner-toast]');
            if (toast) {
                return toast.innerText || toast.textContent;
            }
            
            const toaster = document.querySelector('[data-sonner-toaster]');
            if (toaster) {
                const toasts = toaster.querySelectorAll('li');
                if (toasts.length > 0) {
                    return toasts[toasts.length - 1].innerText || toasts[toasts.length - 1].textContent;
                }
            }
            
            return '';
        """;

        Object result = js.executeScript(script);
        if (result != null) {
            capturedToast = result.toString().trim();
        }
    }
}
