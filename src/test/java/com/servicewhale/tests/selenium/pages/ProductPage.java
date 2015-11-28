package com.servicewhale.tests.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Product page
 */
public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//a[text()='Log out']")
    public WebElement logoutButton;

    @FindBy(how = How.CSS, using = "input#date-from")
    public WebElement dateFrom;

    @FindBy(how = How.CSS, using = "input#date-to")
    public WebElement dateTo;

    @FindBy(how = How.CSS, using = "input[type='checkbox']")
    public WebElement inStock;

    @FindBy(how = How.CSS, using = "input[ng-model='filter.minPrice']")
    public WebElement minPrice;

    @FindBy(how = How.CSS, using = "input[ng-model='filter.maxPrice']")
    public WebElement maxPrice;

    @FindBy(how = How.CSS, using = "select[name='colors']")
    public WebElement color;

    @FindBy(how = How.CSS, using = "button[ng-click='addToCart(item)']")
    public List<WebElement> orderButtons;

    @FindBy(how = How.CSS, using = "a[ng-click='showCart()']")
    public WebElement cartItems;

    @FindBy(how = How.CSS, using = "div[class='modal-body ng-scope']")
    public WebElement cartContent;

    @FindBy(how = How.CSS, using = "button[ng-click='ok()']")
    public WebElement cartOkButton;
}
