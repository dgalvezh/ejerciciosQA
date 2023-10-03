import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage {
    private WebDriver driver;
    private By searchBox = By.name("q");
    private By cookieAcceptButton = By.xpath("//div[@class='QS5gu sy4vM']");

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("https://www.google.com/");
        maximizeWindow();
        acceptCookies();
    }

    private void maximizeWindow() {
        driver.manage().window().maximize();
    }

    private void acceptCookies() {
        try {
            WebElement acceptButton = driver.findElement(cookieAcceptButton);
            acceptButton.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Manejar la excepción si el botón de cookies no está presente en la página
            System.out.println("El botón de cookies no se encontró en la página.");
        }
    }

    public void searchFor(String text) {
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.click();
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
    }
}
