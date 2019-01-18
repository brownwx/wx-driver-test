package com.wx.test;

import com.wx.action.HomeAction;
import com.wx.action.LoginAction;
import com.wx.base.BaseTest;
import com.wx.cases.TestCase;
import com.wx.utils.HttpClient;
import com.wx.utils.ReadExcel;
import com.wx.variable.WxVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static com.wx.variable.WxVariable.*;
import static org.testng.Assert.assertEquals;

/**
 * @author wuxi
 * @date 2019年1月16日
 */
public class TestExcelVoice extends BaseTest {

    private static Logger logger = LoggerFactory.getLogger(TestExcelVoice.class);

    HttpClient httpClient = new HttpClient();

    @BeforeClass
    public void BeforeEcho() throws IOException {

        httpClient.HttpPostJson(NPro.getKey("user"), NPro.getKey("startUrl"));

    }

    @Test
    public void loginEcho() throws InterruptedException {

        Reporter.log("~【开始登陆】: "+"xi.wu~");

        LoginAction loginAction = new LoginAction(driver, WxVariable.LoginPage);

        loginAction.Login("xxxx", "xxxxx");

        Reporter.log("~【登陆成功】: "+"xi.wu~");

    }

    @Test(dataProvider = "getData",dependsOnMethods = {"loginEcho"})
    public void testEcho(TestCase Excelcase) throws InterruptedException, IOException {

        httpClient.HttpPostJson("", NPro.getKey("reset"));

        String goUrl=LBaseUrl+Excelcase.getLanguage()+"/";

        if (driver.getCurrentUrl().equals(goUrl)) {

            logger.info("无需切换语言");

        }else {

            driver.get(LBaseUrl + Excelcase.getLanguage() + "/");
        }

        switch (Excelcase.getBeforeType()) {

            case "battery":
                logger.info("开始设置电量"+" : "+Excelcase.getBeforeDetail());

                httpClient.HttpPostJson(NPro.getKey("Action").replaceAll("kind", Excelcase.getBeforeDetail()),
                        NPro.getKey("setbattery"));

                actionExcel(Excelcase);

                break;

            case "charge":
                logger.info("开始设置回充状态"+" : "+Excelcase.getBeforeDetail());

                httpClient.HttpPostJson(NPro.getKey("Action").replaceAll("kind", Excelcase.getBeforeDetail()),
                        NPro.getKey("setcharge"));
                actionExcel(Excelcase);
                break;

            case "cleantype":
                logger.info("开始设置清扫类型"+" : "+Excelcase.getBeforeDetail());

                httpClient.HttpPostJson(NPro.getKey("Action").replaceAll("kind", Excelcase.getBeforeDetail()),
                        NPro.getKey("setclean"));
                actionExcel(Excelcase);
                break;

            case "cleanact":
                logger.info("开始设置清扫状态"+" : "+Excelcase.getBeforeDetail());

                httpClient.HttpPostJson(NPro.getKey("Action").replaceAll("kind", Excelcase.getBeforeDetail()),
                        NPro.getKey("setcleanact"));
                actionExcel(Excelcase);
                break;

            case "sleep":
                logger.info("开始设置睡眠类型"+" : "+Excelcase.getBeforeDetail());

                httpClient.HttpPostJson(NPro.getKey("Action").replaceAll("kind", Excelcase.getBeforeDetail()),
                        NPro.getKey("setsleep"));
                actionExcel(Excelcase);
                break;

            case "errors":
                logger.info("开始设置故障类型"+" : "+Excelcase.getBeforeDetail());

                httpClient.HttpPostJson(NPro.getKey("Error").replaceAll("kind", Excelcase.getBeforeDetail()),
                        NPro.getKey("nodeUrl"));
                actionExcel(Excelcase);
                break;

            default:

                System.out.println("未知类型");
        }
    }

    @DataProvider(name = "getData")
    public static Iterator<Object[]> getData() throws IOException {

        return ReadExcel.Provider();
    }


    @AfterClass
    public void AfterEcho() throws IOException {

        httpClient.HttpPostJson("", NPro.getKey("reset"));

        httpClient.closeHttp();

    }

    public static void actionExcel(TestCase Excelcase) throws InterruptedException {

        HomeAction homeAction = new HomeAction(driver, WxVariable.TestPage);

        homeAction.wxSendKeys(Excelcase.getAction());

        String response = homeAction.getLastResponse();

        Reporter.log("~【预置条件】: " +Excelcase.getBeforeType()+": "+Excelcase.getBeforeDetail() +" 》 "+"执行" +Excelcase.getAction()+"~");

        Reporter.log("~【实际结果】: "+response+"~");

        logger.info("输入：" + Excelcase.getAction() + " 》》》 " + "回复：" + response);

        assertEquals(response, Excelcase.getExpectedResult());

        Thread.sleep(3000);
    }

}
