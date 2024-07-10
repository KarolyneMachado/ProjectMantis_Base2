package mantis.prova.base2.com.br;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginMantisTest {

    private static final String URL_LOGIN = "https://mantis-prova.base2.com.br/login_page.php";
    private static final String USER_VALID = "Karolyne_Silva";
    private static final String PASSWORD_VALID = "Kmsfs@2306";
    private static final String LOGIN_INVALID = "Login_Invalid";
    private static final String PASSWORD_ERROR = "PasswordError";
    private static final String URL_LOGIN_INVALID = "https://mantis-prova.base2.com.br/login_page.php?error=1&username=Login_Invalid&return=index.php&secure_session=1";
    private WebDriver driver;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
    }

    @BeforeEach
    public void beforeEach(){
        this.driver = new ChromeDriver();
        driver.get(URL_LOGIN);
    }

    @AfterEach
    public void afterEach() {
        this.driver.quit();

    }

    @Test
    public void LoginValid() {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(USER_VALID);
        driver.findElement(By.xpath("//input[@type='submit']")).submit();

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(PASSWORD_VALID);
        driver.findElement(By.xpath("//input[@value='Entrar']")).submit();

        assertNotEquals(driver.getCurrentUrl(), URL_LOGIN);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Karolyne_Silva')]")));
        assertEquals(USER_VALID, element.getText());

    }

    @Test
    public void LoginInvalid() {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(LOGIN_INVALID);
        driver.findElement(By.xpath("//input[@type='submit']")).submit();

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(PASSWORD_ERROR);
        driver.findElement(By.xpath("//input[@value='Entrar']")).submit();

        assertEquals(driver.getCurrentUrl(),
                URL_LOGIN_INVALID);
        assertTrue(driver.getPageSource().contains("Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos."));

    }
}