package mantis.create.task;

import mantis.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CreateTaskPage extends PageObject {

    private static final String URL_CREATE_TASK = "https://mantis-prova.base2.com.br/bug_report_page.php";
    private static final String ERROR_MESSAGE_XPATH = "//p[contains(text(), 'APPLICATION ERROR #11')]"; // XPath da mensagem de erro
    private static final String ISSUE_PAGE_ID = "view-issue-page";


    public CreateTaskPage(WebDriver driver) {
        super(driver);
    }

    public void createTask(String resume, String description, String today, String steps) {
        try {
            selectCategory();
            fillTaskDetails(resume, description, steps);
            submitTask();
            new TaskPage(driver);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar tarefa.", e);
        }
    }

    public void createTaskWithoutSelection(String resume, String description, String today, String steps) {
        try {
            fillTaskDetails(resume, description, steps);
            submitTask();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar tarefa.", e);
        }
    }

    private void fillTaskDetails(String resume, String description, String steps) {
        WebElement summaryField = driver.findElement(By.id("summary"));
        WebElement descriptionField = driver.findElement(By.id("description"));
        WebElement stepsField = driver.findElement(By.id("steps_to_reproduce"));

        summaryField.sendKeys(resume);
        descriptionField.sendKeys(description);
        stepsField.sendKeys(steps);
    }

    private void selectCategory() {
        WebElement selectElement = driver.findElement(By.id("category_id"));
        Select select = new Select(selectElement);
        select.selectByValue("2");
    }

    private void submitTask() {
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();
    }

    public boolean isPageActual() {
        return driver.getCurrentUrl().equals(URL_CREATE_TASK);
    }

    public boolean isApplicationErrorVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10)); // Espera até 10 segundos
            WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_XPATH)));
            return errorMessageElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isIssuePageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement issuePageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ISSUE_PAGE_ID)));
            return issuePageElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
