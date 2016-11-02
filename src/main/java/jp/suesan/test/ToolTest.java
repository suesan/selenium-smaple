package jp.suesan.test;

import jp.suesan.enums.BrowserEnum;
import org.junit.Assume;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * TODO refactor class name
 * Created by suesan on 2016/10/22.
 */
public class ToolTest extends TestBase {

    /**
     * testScenario1 for Chrome
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void testScenario1ForChrome() throws InterruptedException, IOException {
        WebDriver driver = getDriver(BrowserEnum.chrome);
        TestScenario.testScenario1(driver);
    }


    /**
     * testScenario1 for Firefox
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void testScenario1ForFireFox() throws InterruptedException, IOException {
        WebDriver driver = getDriver(BrowserEnum.firefox);
        TestScenario.testScenario1(driver);
    }

    /**
     * testScenario1 for IE
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void testScenario1ForIE() throws InterruptedException, IOException {
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            // TODO skipってこれでいいのか？@ignoreを拡張したほうがいいのか

            WebDriver driver = getDriver(BrowserEnum.internetexplorer);
            TestScenario.testScenario1(driver);
        } else {
            Assume.assumeTrue(false);
        }
    }

}
