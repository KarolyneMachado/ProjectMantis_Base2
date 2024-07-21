package mantis.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import io.qameta.allure.*;

@Epic("User admin")
@Feature("User Login")

public class LoginTests {

    private LoginPage pageOfLogin;

    @BeforeEach
    public void beforeEach(){
        this.pageOfLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.pageOfLogin.close();
    }

    @Test
    @Story("Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Login with valid username and password.")
    public void LoginValid() {
        pageOfLogin.fillInUser("Karolyne_Silva");
        pageOfLogin.enter();
        pageOfLogin.fillInPassword("password12");
        pageOfLogin.loginIn();

        Assert.assertTrue(pageOfLogin.sucessfulLogin());
    }

    @Test
    @Story("Login with invalid credentials")
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: Login with invalid username and password.")
    public void LoginInvalid() {
        pageOfLogin.fillInUser("USER_INVALID");
        pageOfLogin.enter();
        pageOfLogin.fillInPassword("PASSWORD_ERROR");
        pageOfLogin.loginIn();

        Assert.assertTrue(pageOfLogin.accessFailed());
        Assert.assertTrue(pageOfLogin.isMensageLoginInvalidVisible());

    }
}