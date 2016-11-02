package jp.suesan.test;

import jp.suesan.utils.PlatformUtils;
import jp.suesan.utils.ScreenShotUtils;
import org.openqa.selenium.*;
import java.io.IOException;

/**
 * Created by suesan on 2016/10/23.
 */
public class TestScenario {

    /**
     * testScenario1
     * <p>
     * yahooを起動し、検索を実施し、キャプチャを撮る
     *
     * @param driver
     * @throws InterruptedException
     * @throws IOException
     */
    public static void testScenario1(WebDriver driver) throws InterruptedException, IOException {
        driver.get("http://www.yahoo.co.jp");
        WebElement element = driver.findElement(By.name("p")); // get search box element
        element.sendKeys("seleium test java"); // input search keyword
        element.sendKeys(Keys.RETURN);

        // create screenshot file name
        String screenshotName = ScreenShotUtils.createFileName(PlatformUtils.getBrowserName(driver),
                Thread.currentThread().getStackTrace()[1].getMethodName());

        // get screen captured
        ScreenShotUtils.scrollCapture(driver, screenshotName);

        if (driver != null) {
            driver.quit();
        }
    }

    public static void testScenario2(WebDriver driver) {
        // TODO
    }
}
