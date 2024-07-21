package mantis.create.task;

import io.qameta.allure.*;
import mantis.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Epic("User admin")
@Feature("Create task")

public class TaskTests {

    private TaskPage pageOfTask;
    private CreateTaskPage pageOfCreate;

    @BeforeEach
    public void beforeEach() {
        LoginPage pageOfLogin = new LoginPage();
        pageOfLogin.fillInUser("Karolyne_Silva");
        pageOfLogin.enter();
        pageOfLogin.fillInPassword("password12");
        this.pageOfTask = pageOfLogin.loginIn();

        this.pageOfCreate = pageOfTask.loadForm();
    }

    @AfterEach
    public void afterEach() {
        this.pageOfCreate.close();
    }

    @Test
    @Story("Created task")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: successfully created bug report ")
    public void newRegistrationTaskValid(){
        LocalDateTime dateTime = LocalDateTime.now();
        String today = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String resume = "BUG "+today;
        String description = "Foi reportado erro na tela dia:" +today;
        String steps = "1-Etapa cadastrar";

        pageOfCreate.createTask(resume,description, today, steps);

        Assert.assertTrue(pageOfCreate.isIssuePageVisible(), "Página de visualização do problema não visível.");


    }

    @Test
    @Story("Created task")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: created unsuccessful bug report ")
    public void newRegistrationTaskInvalid(){
        LocalDateTime dateTime = LocalDateTime.now();
        String today = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String resume = "BUG "+today;
        String description = "Foi reportado erro na tela dia:" +today;
        String steps = "1-Etapa cadastrar";

        pageOfCreate.createTaskWithoutSelection(resume, description, today, steps);
        Assert.assertTrue(pageOfCreate.isApplicationErrorVisible(), "Mensagem de erro 'APPLICATION ERROR #11' não visível.");


    }
}