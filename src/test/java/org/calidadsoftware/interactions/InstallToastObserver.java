package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Interaction to install a MutationObserver that captures toast messages as they appear
 */
public class InstallToastObserver implements Interaction {

    public static InstallToastObserver now() {
        return new InstallToastObserver();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        String script = """
            if (!window.lastToastMessage) {
                window.lastToastMessage = '';
                console.log('[ToastObserver] Installing mutation observer');
                
                // Create a MutationObserver to catch toasts as they appear
                const observer = new MutationObserver((mutations) => {
                    for (const mutation of mutations) {
                        if (mutation.type === 'childList') {
                            for (const node of mutation.addedNodes) {
                                if (node.nodeType === 1) {
                                    // Check if the node itself is a toast
                                    let toast = null;
                                    if (node.querySelector) {
                                        toast = node.querySelector('[data-sonner-toast]');
                                    }
                                    if (!toast && node.hasAttribute && node.hasAttribute('data-sonner-toast')) {
                                        toast = node;
                                    }
                                    
                                    if (toast) {
                                        const text = toast.innerText || toast.textContent;
                                        if (text && text.trim()) {
                                            window.lastToastMessage = text.trim();
                                            console.log('[ToastObserver] Captured toast:', window.lastToastMessage);
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
                
                // Observe the entire document for toast additions
                observer.observe(document.body, { childList: true, subtree: true });
                console.log('[ToastObserver] Observer installed successfully');
            } else {
                console.log('[ToastObserver] Observer already installed');
            }
        """;
        
        js.executeScript(script);
    }
}
