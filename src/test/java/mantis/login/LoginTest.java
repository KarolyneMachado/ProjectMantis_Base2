package mantis.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class LoginTest {

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
    public void LoginValid() {
        pageOfLogin.fillInUser("Karolyne_Silva");
        pageOfLogin.enter();
        pageOfLogin.fillInPassword("Kmsfs@2306");
        pageOfLogin.loginIn();

        Assert.assertTrue(pageOfLogin.sucessfulLogin());
    }

    @Test
    public void LoginInvalid() {
        pageOfLogin.fillInUser("USER_INVALID");
        pageOfLogin.enter();
        pageOfLogin.fillInPassword("PASSWORD_ERROR");
        pageOfLogin.loginIn();

        Assert.assertTrue(pageOfLogin.accessFailed());
        Assert.assertTrue(pageOfLogin.isMensageLoginInvalidVisible());

    }
}