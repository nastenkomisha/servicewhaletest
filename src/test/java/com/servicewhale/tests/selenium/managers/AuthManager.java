package com.servicewhale.tests.selenium.managers;

import com.servicewhale.tests.selenium.JUnitTestBase;
import com.servicewhale.tests.selenium.pages.AuthPage;
import com.servicewhale.tests.selenium.pages.ProductPage;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Auth manager
 */
public class AuthManager extends JUnitTestBase {
    private static AuthPage authPage = PageFactory.initElements(driver, AuthPage.class);
    private static ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
    WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);

    @Step("Sign up")
    public void signUp(String email, String password) {
        authPage.signUpTab.click();
        wait.until(ExpectedConditions.visibilityOf(authPage.signUpButton));
        authPage.email.sendKeys(email);
        authPage.password.sendKeys(password);
        authPage.confirmPassword.sendKeys(password);
        authPage.signUpButton.click();
    }

    @Step("Log in")
    public void logIn(String email, String password)  {
        authPage.loginTab.click();
        wait.until(ExpectedConditions.visibilityOf(authPage.logInButton));
        authPage.email.sendKeys(email);
        authPage.password.sendKeys(password);
        authPage.logInButton.click();
        wait.until(ExpectedConditions.visibilityOf(productPage.logoutButton));
        makeScreenshot("The user " + email + " should be logged in");
    }
}

