import org.openqa.selenium.*;

import java.io.File;

public class WikipediaPage {
    private WebDriver driver;
    private By targetLink = By.xpath("//a[@href='https://es.wikipedia.org/wiki/Automatizaci%C3%B3n']");
    private By seccionText = By.xpath("//p[contains(.,'primer proceso')]");
    public WikipediaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTargetLink() {
        driver.findElement(targetLink).click();
    }

    public void scrollSeccion() {
        WebElement elemento = driver.findElement(seccionText);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center' });", elemento);
        js.executeScript("arguments[0].style.border='2px solid red';", elemento);
    }

    public void capturaPantalla(){
        // Tomar una captura de pantalla
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenshotFile.renameTo(new File("ej2/src/screenshot/screenshot.png"));

    }

}
