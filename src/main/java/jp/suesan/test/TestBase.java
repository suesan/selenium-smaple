package jp.suesan.test;

import jp.suesan.utils.PlatformUtils;
import jp.suesan.enums.BrowserEnum;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by suesan on 2016/10/22.
 */
public class TestBase {

    /**
     * webdriver
     */
    private WebDriver driver = null;



    /**
     * getDriver
     *
     * @param browser Browser Type
     */
    public WebDriver getDriver(BrowserEnum browser) {
        switch (browser) {
            case internetexplorer:
                this.driver = new InternetExplorerDriver();
                break;
            case firefox:
                this.driver = new FirefoxDriver();
                break;
            default:
                this.driver = new ChromeDriver();
                break;
        }
        return this.driver;
    }


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", getGeckoDriverPath());
        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
        System.setProperty("webdriver.ie.driver", "driver/windows/IEDriverServer.exe");
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }


    /**
     * getGeckoDriverPath
     * <p>
     * get the gecko driver path.
     *
     * @return gecko driver path
     * @see https://github.com/mozilla/geckodriver/releases
     */
    private String getGeckoDriverPath() {
        String driverPath = "driver/linux/geckodriver";
        if (PlatformUtils.isMac()) {
            driverPath = "driver/mac/geckodriver";
        } else if (PlatformUtils.isWindows()) {
            driverPath = "driver/windows/geckodriver.exe";
        }

        return driverPath;
    }

    /**
     * getChromeDriverPath
     * <p>
     * get the chrome driver path
     *
     * @return chrome driver path
     * @see https://sites.google.com/a/chromium.org/chromedriver/downloads
     */
    private String getChromeDriverPath() {
        String driverPath = "driver/linux/chromedriver";
        if (PlatformUtils.isMac()) {
            driverPath = "driver/mac/chromedriver";
        } else if (PlatformUtils.isWindows()) {
            driverPath = "driver/windows/chromedriver.exe";
        }

        return driverPath;
    }
}
