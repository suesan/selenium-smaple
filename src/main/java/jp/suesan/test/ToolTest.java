package jp.suesan.test;

import jp.suesan.enums.BrowserEnum;
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
     * testScenario2 for Chrome
     * TODO
     */
    @Test
    public void testScenario2ForChrome() {
        WebDriver driver = getDriver(BrowserEnum.chrome);
        TestScenario.testScenario2(driver);
    }

}
