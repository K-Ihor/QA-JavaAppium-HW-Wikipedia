package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject
{
    private static final String
            LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Can`t find auth button.", 10);
        this.waitForElementAndClick(LOGIN_BUTTON, "Can`t find click auth button", 10);
    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "can`t find and put a login input", 10);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "can`t find and put a password input", 10);
    }

    public void submitFrom(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Can`t find click submit auth button", 10);
    }
}