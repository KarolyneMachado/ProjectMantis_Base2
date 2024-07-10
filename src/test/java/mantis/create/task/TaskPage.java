package mantis.create.task;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TaskPage {

    private static final String URL_CREATE_TASK = "https://mantis-prova.base2.com.br/bug_report_page.php";

    private WebDriver driver;

    public TaskPage(WebDriver driver){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        this.driver = driver;
    }

    public void close() {
        this.driver.quit();
    }

    public CreateTaskPage loadForm() {
        this.driver.navigate().to(URL_CREATE_TASK);
        return new CreateTaskPage(driver);
    }
}

