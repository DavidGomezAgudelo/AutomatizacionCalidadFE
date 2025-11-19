package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.InventoryPage;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class CartItemCount {

    // pregunta para obtener el numero de items en el carrito
    public static Question<Integer> value() {
        return actor -> {
            String badgeText = Text.of(InventoryPage.CART_BADGE).answeredBy(actor);
            return badgeText.isEmpty() ? 0 : Integer.parseInt(badgeText);
        };
    }
}
