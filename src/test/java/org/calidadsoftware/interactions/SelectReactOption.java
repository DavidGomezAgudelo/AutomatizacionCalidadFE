package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Interaction to select options in React dropdowns with hidden selects
 * Searches through all hidden selects to find the one with the matching option
 * value
 * Works both inside dialogs (edit/create modals) and in the main page
 */
public class SelectReactOption implements Interaction {

    private final String value;

    public SelectReactOption(String value) {
        this.value = value;
    }

    public static SelectReactOption withValue(String value) {
        return Tasks.instrumented(SelectReactOption.class, value);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        // Search through all hidden selects to find the one with matching option
        // Works both in dialogs and main page
        js.executeScript(
                "var container = document.querySelector('[role=\"dialog\"]') || document;" +
                        "var selects = container.querySelectorAll('select[aria-hidden=\"true\"]');" +
                        "for(var i=0; i<selects.length; i++) {" +
                        "  var options = selects[i].options;" +
                        "  for(var j=0; j<options.length; j++) {" +
                        "    if(options[j].value === arguments[0]) {" +
                        "      var select = selects[i];" +
                        "      select.selectedIndex = j;" +
                        // Trigger React events by accessing the React fiber
                        "      var reactKey = Object.keys(select).find(key => key.startsWith('__reactProps') || key.startsWith('__reactEventHandlers'));"
                        +
                        "      if (reactKey && select[reactKey] && select[reactKey].onChange) {" +
                        "        try {" +
                        "          var event = { target: select, currentTarget: select, preventDefault: function(){}, stopPropagation: function(){} };"
                        +
                        "          select[reactKey].onChange(event);" +
                        "        } catch(e) { console.log('React onChange error:', e); }" +
                        "      }" +
                        // Dispatch native events
                        "      select.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "      select.dispatchEvent(new Event('change', { bubbles: true }));" +
                        // Also trigger on the parent button if it exists
                        "      var button = select.previousElementSibling;" +
                        "      if (button && button.tagName === 'BUTTON') {" +
                        "        button.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "      }" +
                        "      return true;" +
                        "    }" +
                        "  }" +
                        "}" +
                        "return false;",
                value);
    }
}
