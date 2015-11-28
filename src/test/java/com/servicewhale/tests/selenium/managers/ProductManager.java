package com.servicewhale.tests.selenium.managers;

import com.servicewhale.tests.selenium.JUnitTestBase;
import com.servicewhale.tests.selenium.pages.AuthPage;
import com.servicewhale.tests.selenium.pages.ProductPage;
import org.assertj.core.api.JUnitSoftAssertions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Product manager
 */
public class ProductManager extends JUnitTestBase {
    private ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
    private AuthPage authPage = PageFactory.initElements(driver, AuthPage.class);
    private String date = "2015/07/03";
    private String price = "11";
    private WebDriverWait wait = new WebDriverWait(driver,defaultTimeout);
    private JUnitSoftAssertions softly;

    public ProductManager(JUnitSoftAssertions softly){
        this.softly=softly;
    }

    @Step("Log out")
    public void logOut() {
        wait.until(ExpectedConditions.elementToBeClickable(productPage.logoutButton));
        productPage.logoutButton.click();
        wait.until(ExpectedConditions.visibilityOf(authPage.email));
        makeScreenshot("The user should be logged out");
    }

    @Step("Search")
    public void productSearch() {
        productPage.dateFrom.click();
        productPage.dateFrom.sendKeys(date);
        productPage.maxPrice.click();
        if (browser.equals("firefox")){
            productPage.maxPrice.sendKeys("1");
            productPage.maxPrice.sendKeys("1");
        }
        else
            productPage.maxPrice.sendKeys(price);
        productPage.dateTo.click();
        softly.assertThat(productPage.orderButtons.size()).isEqualTo(1);
        makeScreenshot("The only one item should be displayed after filtering");
    }

    @Step("Search")
    public void addingToCart() {
        productPage.orderButtons.get(0).click();
        softly.assertThat(productPage.cartItems.getText()).isEqualTo("Cart: 1 items");
        makeScreenshot("One item should be in the cart");
    }

    @Step("Search")
    public void viewContentOfCart() {
        productPage.cartItems.click();
        softly.assertThat(productPage.cartContent.getText()).isEqualTo("Test3");
        makeScreenshot("Cart items");
        productPage.cartOkButton.click();
    }



}

