package jp.suesan.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by suesan on 2016/10/22.
 */
public class PlatformUtils {
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    public static boolean isLinux() {
        return OS_NAME.startsWith("linux");
    }
    public static boolean isMac() {
        return OS_NAME.startsWith("mac");
    }
    public static boolean isWindows() {
        return OS_NAME.startsWith("windows");
    }

    public static String getBrowserName(WebDriver driver) {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        return browserName;
    }
}
