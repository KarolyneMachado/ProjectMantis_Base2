package mantis.create.task;

import mantis.PageObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TaskPage extends PageObject {

    private static final String URL_CREATE_TASK = "https://mantis-prova.base2.com.br/bug_report_page.php";

    public TaskPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public CreateTaskPage loadForm() {
        this.driver.navigate().to(URL_CREATE_TASK);
        return new CreateTaskPage(driver);
    }
}

