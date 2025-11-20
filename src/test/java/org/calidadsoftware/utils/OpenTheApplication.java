package org.calidadsoftware.utils;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

// tarea para abrir la aplicacion en la url indicada
public class OpenTheApplication {

    public static Performable on(String url) {
        return Task.where("{0} abre la aplicación",
                net.serenitybdd.screenplay.actions.Open.url(url)
        );
    }
}