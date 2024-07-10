package mantis.login;

import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

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
    @DisplayName("Teste Login valido")
    @Description("Teste para verificar funcionamento login")
    public void LoginValid() {
        pageOfLogin.fillInUser("Karolyne_Silva");
        pageOfLogin.enter();
        pageOfLogin.fillInPassword("Kmsfs@2306");
        pageOfLogin.loginIn();

        Assert.assertTrue(pageOfLogin.sucessfulLogin());
    }

    @Test
    @DisplayName("Teste Login com credencias invalido")
    @Description("Teste para verificar seguranca")
    public void LoginInvalid() {
        pageOfLogin.fillInUser("USER_INVALID");
        pageOfLogin.enter();
        pageOfLogin.fillInPassword("PASSWORD_ERROR");
        pageOfLogin.loginIn();

        Assert.assertTrue(pageOfLogin.accessFailed());
        Assert.assertTrue(pageOfLogin.isMensageLoginInvalidVisible());

    }
}