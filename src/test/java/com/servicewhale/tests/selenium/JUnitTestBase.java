package com.servicewhale.tests.selenium;

import com.servicewhale.tests.selenium.util.PropertyLoader;
import com.servicewhale.tests.selenium.util.WaitUntilAction;
import org.apache.commons.lang3.time.StopWatch;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.util.concurrent.TimeUnit;

/**
 * Base class for all the JUnit-based test classes
 */
public class JUnitTestBase {
    protected static String baseUrl;
    protected static String email;
    protected static String password;
    protected static Capabilities capabilities;
    protected static Long defaultTimeout;
    public static String browser;
    public static WebDriver driver;

    @ClassRule
    public static ExternalResource webDriverProperties = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            baseUrl = PropertyLoader.loadProperty("site.url");
            baseUrl = PropertyLoader.loadProperty("site.url");
            email = PropertyLoader.loadProperty("email");
            password = PropertyLoader.loadProperty("password");
            defaultTimeout = Long.valueOf(PropertyLoader.loadProperty("defaultTimeout")).longValue();
            capabilities = PropertyLoader.loadCapabilities();
            browser = capabilities.getBrowserName();
        }
    };

    @ClassRule
    public static ExternalResource webDriver = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            if (System.getProperty("os.name").equals("Linux")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            }

            if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
            }
            driver = WebDriverFactory.getDriver(capabilities);
            driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
    };

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] makeScreenshot(String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/html")
    public static byte[] saveHtml(String fileName) {
        return driver.getPageSource().getBytes();
    }

    protected static void waitUntilActionIsPassed(WaitUntilAction action, String message) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        executeAction(stopWatch, action, message);
    }

    protected static void waitUntilActionIsPassed(WaitUntilAction action, String message, long customTimeout) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        executeAction(stopWatch, action, message, customTimeout);
    }

    private static void executeAction(StopWatch stopWatch, WaitUntilAction action, String message) throws Exception {
        try {
            action.action();

        } catch (Exception ex) {
            if (stopWatch.getTime() / 1000 > defaultTimeout) {
                makeScreenshot(message);
                Assert.fail(message + "\n Exception message: " + ex.getMessage());
            } else executeAction(stopWatch, action, message);
        }
    }

    private static void executeAction(StopWatch stopWatch, WaitUntilAction action, String message, long customTimeout) throws Exception {
        try {
            action.action();

        } catch (Exception ex) {
            if (stopWatch.getTime() / 1000 > customTimeout) {
                makeScreenshot(message);
                Assert.fail(message);
            } else executeAction(stopWatch, action, message, customTimeout);
        }
    }
}
