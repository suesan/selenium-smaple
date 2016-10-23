package jp.suesan.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by suesan on 2016/10/23.
 */
public class ScreenShotUtils {

    private static final String DATE_PATTERN_YYYYMMDDHHMMSS ="yyyyMMddHHmmss";
    /**
     * scrollCapture
     *
     * chromeでうまく動かない…。。。
     *
     * @param driver WebDriver
     * @param filename filename
     * @throws IOException
     * @see http://qiita.com/Akihisa-Tokuda/items/468c071b7229264baa62
     */
    public static void scrollCapture(WebDriver driver, String filename) throws IOException {

        driver.switchTo().defaultContent();
        TakesScreenshot ts = (TakesScreenshot) new Augmenter().augment(driver);

        JavascriptExecutor jexec = (JavascriptExecutor) driver;

        int innerH = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerHeight")));
        int innerW = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerWidth")));
        int scrollH = Integer.parseInt(String.valueOf(jexec.executeScript("return document.documentElement.scrollHeight")));

        BufferedImage img = new BufferedImage(innerW, scrollH, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();

        if (innerH > scrollH) {
            BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
            g.drawImage(imageParts, 0, 0, null);
        } else {
            int scrollableH = scrollH;
            int i = 0;

            while (scrollableH > innerH) {
                BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
                g.drawImage(imageParts, 0, innerH * i, null);
                scrollableH = scrollableH - innerH;
                i++;
                jexec.executeScript("window.scrollTo(0," + innerH * i + ")");
            }

            BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
            g.drawImage(imageParts, 0, scrollH - innerH, null);
        }

        ImageIO.write(img, "png", new File("screenshots/" + filename));
    }

    /**
     * createFileName
     * @param browserName browser name
     * @param methodName test scenario(method) name
     * @return
     */
    public static String createFileName(String browserName, String methodName) {
        Calendar cal = Calendar.getInstance();
        String date = new SimpleDateFormat(DATE_PATTERN_YYYYMMDDHHMMSS).format(cal.getTimeInMillis());
        return String.format("%s/%s-%s.png", browserName, methodName, date);
    }
}
