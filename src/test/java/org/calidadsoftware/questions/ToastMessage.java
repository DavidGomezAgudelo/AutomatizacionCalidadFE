package org.calidadsoftware.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Question to get the text of toast messages (Sonner toasts)
 * Waits for toast to appear and returns its text
 */
public class ToastMessage implements Question<String> {

    public static ToastMessage displayed() {
        return new ToastMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        // Wait up to 10 seconds for toast to appear
        for (int i = 0; i < 20; i++) {
            try {
                // First check if observer already captured it
                Object lastMessage = js.executeScript("return window.lastToastMessage || '';");
                if (lastMessage != null && !lastMessage.toString().trim().isEmpty()) {
                    String message = lastMessage.toString().trim();
                    // Clear it for next time
                    js.executeScript("window.lastToastMessage = '';");
                    return message;
                }
                
                // Also try direct search
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
                    
                    return null;
                """;
                
                Object result = js.executeScript(script);
                if (result != null) {
                    String toastText = result.toString().trim();
                    if (!toastText.isEmpty()) {
                        return toastText;
                    }
                }
                
                Thread.sleep(500);
            } catch (Exception e) {
                // Continue waiting
            }
        }
        
        // Return empty string if no toast found after waiting
        return "";
    }
}
