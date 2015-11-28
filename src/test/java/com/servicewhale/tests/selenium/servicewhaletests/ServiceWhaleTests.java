package com.servicewhale.tests.selenium.servicewhaletests;

import com.servicewhale.tests.selenium.JUnitTestBase;
import com.servicewhale.tests.selenium.managers.AuthManager;
import com.servicewhale.tests.selenium.managers.ProductManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * ServiceWhale test suite.
 */
@Title("ServiceWhale test suite")
public class ServiceWhaleTests extends JUnitTestBase {
    public AuthManager authManager = new AuthManager();
    public ProductManager productManager = new ProductManager(softly);

    @BeforeClass
    public static void beforeClass() {
        driver.get(baseUrl);
    }

    @AfterClass
    public static void afterClass() {
        WebDriverFactory.dismissAll();
    }

    @Test
    @Title("ServiceWhale test")
    public void serviceWhaleTest() {
        authManager.signUp(email, password);
        authManager.logIn(email, password);
        productManager.productSearch();
        productManager.addingToCart();
        productManager.viewContentOfCart();
        productManager.logOut();
    }
}
