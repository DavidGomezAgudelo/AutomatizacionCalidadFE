package org.calidadsoftware.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.calidadsoftware.interactions.WaitFor;
import org.calidadsoftware.interfaces.LoginPage;
import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.interactions.ClickOn;

// tarea para realizar el login en la aplicacion
public class Login  {

    // crea una tarea de login con las credenciales proporcionadas
    public static Performable with(String username, String password) {
        return Task.where("{0} intenta iniciar sesión",
                EnterText.valueInto(username, LoginPage.USERNAME),
                WaitFor.sleep(2),
                EnterText.valueInto(password, LoginPage.PASSWORD),
                WaitFor.sleep(2),
                ClickOn.target(LoginPage.LOGIN_BUTTON),
                WaitFor.sleep(2)
                );
    }
}

