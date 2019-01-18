package com.wx.base;

import com.wx.driver.DriverType;
import com.wx.driver.WxWebdriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import static com.wx.variable.WxVariable.BaseUrl;

/**
 * @author wuxi
 * @date 2019年1月8日
 */
public class BaseTest  {

    public static WxWebdriver driver;

    public WxWebdriver getDriver() {

        return driver;
    }

    @BeforeTest
    public void beforeClass() throws MalformedURLException {

        driver = new WxWebdriver();

        driver.manage();

        driver.get(BaseUrl);


    }

    @AfterTest
    public void afterClass() {

        driver.quit();
    }

}
