package mantis.create.task;

import mantis.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class CreateTaskPage extends PageObject {

    private static final String URL_CREATE_TASK = "https://mantis-prova.base2.com.br/bug_report_page.php";
    private static final String URL_CREATE_TASK_ERROR = "https://mantis-prova.base2.com.br/bug_report.php?posted=1";



    public CreateTaskPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public TaskPage createTask(String resume, String description, String today, String steps){

        WebElement selectElement = driver.findElement(By.id("category_id"));
        Select select = new Select(selectElement);
        select.selectByValue("2");

        this.driver.findElement(By.id("summary")).sendKeys(resume);
        this.driver.findElement(By.id("description")).sendKeys(description);
        this.driver.findElement(By.id("steps_to_reproduce")).sendKeys(steps);

        WebElement submitButton = this.driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();

        return new TaskPage(driver);

    }


    public boolean isPageActual() {
        return driver.getCurrentUrl().equals(URL_CREATE_TASK);
    }
}