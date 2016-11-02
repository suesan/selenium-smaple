package jp.suesan.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.suesan.utils.PlatformUtils;
import jp.suesan.enums.BrowserEnum;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by suesan on 2016/10/22.
 */
public class TestBase {

    private File report;
    private BufferedWriter writer;

    /**
     * webdriver
     */
    private static WebDriver driver = null;

    private static File junitReport;
    private static BufferedWriter junitWriter;

    private static TestResult testResult;
    private static List<Result> resultList;


    @BeforeClass
    public static void setUp() throws IOException {
        System.setProperty("webdriver.gecko.driver", getGeckoDriverPath());
        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
        System.setProperty("webdriver.ie.driver", "driver/windows/IEDriverServer.exe");

        testResult = new TestResult();
        testResult.startDate = getDatetime("yyyy-MM-dd'T'HH:mm:ss'Z'");
        resultList = new ArrayList<Result>();

    }

    @AfterClass
    public static void tearDown() throws Exception {
        testResult.endDate = getDatetime("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String reportFile = System.getProperty("user.dir")
                + "/report.json";
        junitReport = new File(reportFile);
        junitWriter = new BufferedWriter(new FileWriter(junitReport));
        testResult.results = resultList;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(testResult);

        junitWriter.write(json);
        junitWriter.close();
    }

    @Rule
    public TestRule watchman = new TestWatcher() {
        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            Result result = new Result();
            result.scenario = description.getMethodName();
            result.result = "success";
            resultList.add(result);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            Result result = new Result();
            result.scenario = description.getMethodName();
            result.result = "failed";
            resultList.add(result);
        }

        @Override
        protected void skipped(AssumptionViolatedException e,
                               Description description) {
            Result result = new Result();
            result.scenario = description.getMethodName();
            result.result = "skipped";
            resultList.add(result);
        }

    };

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

    /**
     * getGeckoDriverPath
     * <p>
     * get the gecko driver path.
     *
     * @return gecko driver path
     * @see https://github.com/mozilla/geckodriver/releases
     */
    private static String getGeckoDriverPath() {
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
    private static String getChromeDriverPath() {
        String driverPath = "driver/linux/chromedriver";
        if (PlatformUtils.isMac()) {
            driverPath = "driver/mac/chromedriver";
        } else if (PlatformUtils.isWindows()) {
            driverPath = "driver/windows/chromedriver.exe";
        }

        return driverPath;
    }

    private static String getDatetime(String format) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(cal.getTimeZone());
        return df.format(cal.getTime());
    }
}
