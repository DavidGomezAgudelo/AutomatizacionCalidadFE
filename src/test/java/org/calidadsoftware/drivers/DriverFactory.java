package org.calidadsoftware.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

// fabrica de drivers para configurar diferentes navegadores
public class DriverFactory {

    // crea edge driver con ventana maximizada (nota: metodo se llama chrome pero retorna edge)
    public static WebDriver chrome() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        return new EdgeDriver(options);
    }

    // crea edge driver basico sin opciones adicionales
    public static WebDriver edge() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    // crea firefox driver con resolucion personalizada 1280x800
    public static WebDriver firefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1280", "--height=800");
        return new FirefoxDriver(options);
    }
}
