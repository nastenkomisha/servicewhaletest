package com.servicewhale.tests.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Auth page
 */
public class AuthPage extends Page {
    public AuthPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.CSS, using = "a[ui-sref='login']")
    public WebElement loginTab;

    @FindBy(how = How.CSS, using = "a[ui-sref='signup']")
    public WebElement signUpTab;

    @FindBy(how = How.CSS, using = "input#inputEmail")
    public WebElement email;

    @FindBy(how = How.CSS, using = "input#inputPassword1")
    public WebElement password;

    @FindBy(how = How.CSS, using = "input#inputPassword2")
    public WebElement confirmPassword;

    @FindBy(how = How.XPATH, using = "//button[text()='Sign up']")
    public WebElement signUpButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Log in']")
    public WebElement logInButton;





}
