/**
 *
 */
package com.wx.driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import static com.wx.variable.WxVariable.*;

/**
 * @author wuxi
 * @date 2019年1月8日
 */
public class WxWebdriver {

    private static WebDriver driver;

    //docker运行
    public WxWebdriver() throws MalformedURLException {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        capabilities.setBrowserName("chrome");
        //capabilities.setBrowserName("firefox");
        //加载启动项
        capabilities.setCapability("chromeOptions", chromeOptions());

        driver = new RemoteWebDriver(new URL("http://10.88.41.38:4444/wd/hub"),capabilities);

    }

    //本地运行
    public WxWebdriver(DriverType type) {

        switch (type) {
            case Firefox:
                System.setProperty("webdriver.firefox.driver", FireFoxPath);
                driver = new FirefoxDriver();
                break;
            case Safari:
                System.setProperty("webdriver.safari.driver", SafariPath);
                driver = new SafariDriver();
                break;
            case Chrome:
                System.setProperty("webdriver.chrome.driver", ChromePath);
                driver = new ChromeDriver(chromeOptions());
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%浏览器类型", type));
        }
    }

    public void implicitlyWait(int timeout) {

        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public WebElement findElement(By by) {
            WebElement element = driver.findElement(by);
            return element;

    }

    public List<WebElement> findElements(By by) {

        List<WebElement> elements = driver.findElements(by);
        return elements;

    }

    public String getCurrentUrl(){

      return   driver.getCurrentUrl();
    }

    public void manage() {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// 识别元素时的超时时间
        // driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);//页面加载时的超时时间
        // driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);//异步脚本的超时时间
    }

    public void get(String string) {

        driver.get(string);

    }

    public void quit() {

        driver.quit();

    }

    public static boolean switchToWindow(String windowTitle) {
        boolean flag = false;
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for (String s : handles) {
                if (s.equals(currentHandle))
                    continue;
                else {
                    driver.switchTo().window(s);
                    if (driver.getTitle().contains(windowTitle)) {
                        flag = true;
                        System.out.println("Switch to window: " + windowTitle + " successfully!");
                        break;
                    } else
                        continue;
                }
            }
        } catch (NoSuchWindowException e) {
            System.out.printf("Window: " + windowTitle + " cound not found!", e.fillInStackTrace());
            flag = false;
        }
        return flag;
    }

    public String getTitle() {

        return driver.getTitle();

    }

    public static ChromeOptions chromeOptions() {

        Map<String, Object> prefs = new HashMap<String, Object>();

        ChromeOptions options = new ChromeOptions();

        //options.addArguments("headless");//无界面参数
        //options.addArguments("no-sandbox");//禁用沙盒

        //禁用插件
        //options.addArguments("--disable-plugins","--disable-images","--start-maximized","--disable-javascript");
        //options.addArguments("–-user-data-dir==/Users/wuxi/Library/Application Support/Google/Chrome");

        //prefs.put("profile.managed_default_content_settings.images", 2);
        //prefs.put("profile.default_content_setting_values.notifications", 2);

        options.setExperimentalOption("prefs", prefs);

        return options;
    }

    /**
     * 全屏截图
     */
    public void takeScreen(String path, String fileName) throws Exception {

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(srcFile, new File(path + "/" + fileName + ".png"));

    }


}
