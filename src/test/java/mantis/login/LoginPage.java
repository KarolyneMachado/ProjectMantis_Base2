package mantis.login;

import mantis.PageObject;
import mantis.create.task.TaskPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends PageObject {

    private static final String URL_LOGIN = "https://mantis-prova.base2.com.br/login_page.php";
    private static final String MENSAGE_FAIL = "Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.";

    public LoginPage(){
        super(null);
        driver.get(URL_LOGIN);
    }

    public void fillInUser(String username) {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
    }

    public void enter() {
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
    }

    public void fillInPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
    }

    public TaskPage loginIn() {
        driver.findElement(By.xpath("//input[@value='Entrar']")).submit();
        return new TaskPage(driver);
    }

    public boolean accessFailed() {
        return driver.getCurrentUrl().contains(URL_LOGIN);
    }

    public boolean isMensageLoginInvalidVisible() {
        return driver.getPageSource().contains(MENSAGE_FAIL);
    }

    public boolean sucessfulLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return driver.getCurrentUrl().contains("index.php");
    }
}