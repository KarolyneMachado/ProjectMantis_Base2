package mantis.create.task;

import mantis.login.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;

public class TaskTest {

    private TaskPage pageOfTask;
    private CreateTaskPage pageOfCreate;

    @BeforeEach
    public void beforeEach() {
        LoginPage pageOfLogin = new LoginPage();
        pageOfLogin.fillInUser("Karolyne_Silva");
        pageOfLogin.enter();
        pageOfLogin.fillInPassword("password12");
        this.pageOfTask = pageOfLogin.loginIn();
        CreateTaskPage pageOfCreate = pageOfTask.loadForm();

        this.pageOfCreate = pageOfTask.loadForm();
    }

    @Test
    public void newRegistrationTaskValid(){
        LocalDateTime dateTime = LocalDateTime.now();
        String today = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String resume = "BUG "+today;
        String description = "Foi reportado erro na tela dia:" +today;
        String steps = "1-Etapa cadastrar";

        this.pageOfTask = pageOfCreate.createTask(resume,description, today, steps);
    }
}