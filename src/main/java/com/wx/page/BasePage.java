package com.wx.page;


import com.wx.driver.WxWebdriver;
import com.wx.utils.GetLocatorUtils;

/**
 * @author wuxi
 * @date 2019年1月14日
 */
public class BasePage {

    public  WxWebdriver driver;
    public  GetLocatorUtils locator;

    //page引入页面元素
    public BasePage(WxWebdriver driver, String configfile) {

        this.driver = driver;
        this.locator = new GetLocatorUtils(configfile);

    }

}
