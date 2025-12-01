package org.calidadsoftware.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

// fabrica de drivers para configurar diferentes navegadores
public class DriverFactory {

    // crea chrome driver con ventana maximizada
    public static WebDriver chrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-search-engine-choice-screen");
        return new ChromeDriver(options);
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