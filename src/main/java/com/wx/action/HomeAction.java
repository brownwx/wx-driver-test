package com.wx.action;

import com.wx.driver.WxWebdriver;
import com.wx.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.util.List;


/**
 * @author wuxi
 * @date 2019年1月8日
 */
public class HomeAction extends BasePage {

    public HomeAction(WxWebdriver driver, String configfile) {
        super(driver, configfile);
    }

    public void wxSendKeys(String input) throws InterruptedException {

        //Select Select= new Select(driver.findElement(locator.getByLocalor("selectLanguage")));
        //Select.selectByValue(la);

        driver.findElement(locator.getByLocalor("testPageIput")).sendKeys(input);

        driver.findElement(locator.getByLocalor("testPageIput")).sendKeys(Keys.ENTER);

        Thread.sleep(15000);

    }

    public String getLastResponse() throws InterruptedException {

        List<WebElement> elements=driver.findElements(locator.getByLocalor("checkPageOut"));

        int index = elements.size();

        String check = elements.get(index-1).getText();

        return check;

    }

}
