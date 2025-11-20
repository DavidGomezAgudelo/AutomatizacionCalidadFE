package org.calidadsoftware.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Actor;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.LoginPage;
import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.interactions.ClickOn;

// tarea para realizar el login en la aplicacion
public class Login implements Performable {
    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void performAs(Actor actor) {
        actor.attemptsTo(
            EnterText.valueInto(username, LoginPage.USERNAME),
            WaitFor.sleep(2),
            EnterText.valueInto(password, LoginPage.PASSWORD),
            WaitFor.sleep(2),
            ClickOn.target(LoginPage.LOGIN_BUTTON),
            WaitFor.sleep(2)
        );
    }

    // crea una tarea de login con las credenciales proporcionadas
    public static Performable with(String username, String password) {
        return new Login(username, password);
    }
}
