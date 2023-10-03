import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class WikipediaTest {
    public static void main(String[] args) throws InterruptedException{

        String driverPath = "ej2/src/chromedriver/chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver();

        // Crear objetos de Page Object
        GooglePage googlePage = new GooglePage(driver);
        WikipediaPage wikipediaPage = new WikipediaPage(driver);

        // Ir a la página de Google y realizar la búsqueda
        googlePage.goTo();
        googlePage.searchFor("Automatización");

        // Ir a la página de Wikipedia y aceptar cookies
        wikipediaPage.clickTargetLink();

        // Realizar scroll a la sección
        wikipediaPage.scrollSeccion();

        //Realizar la captura
        wikipediaPage.capturaPantalla();
        // Cerrar el navegador
        driver.quit();
    }
}
