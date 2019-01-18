package com.wx.action;

import com.wx.driver.WxWebdriver;
import com.wx.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

/**
 * @author wuxi
 * @date 2019年1月8日
 */
public class LoginAction extends BasePage {

    public LoginAction(WxWebdriver driver, String configfile){

        super(driver, configfile);
    }

    public void Login(String user,String password) throws InterruptedException {

        driver.findElement(locator.getByLocalor("login_user")).sendKeys(user);
        driver.findElement(locator.getByLocalor("login_password")).sendKeys(password);
        driver.findElement(locator.getByLocalor("login_in")).click();

        Thread.sleep(7000);

        System.out.println(driver.getTitle());
    }

}
