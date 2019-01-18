package com.wx.test;

import com.wx.action.HomeAction;
import com.wx.action.LoginAction;
import com.wx.base.BaseTest;
import com.wx.variable.WxVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import java.io.IOException;
import static org.testng.Assert.assertEquals;
import static com.wx.utils.GetCsv.getTestData;
import static com.wx.variable.WxVariable.CsvData;

/**
 * @author wuxi
 * @date 2019年1月9日
 */
public class TestCsvVoice extends BaseTest {

    private static Logger logger = LoggerFactory.getLogger(TestCsvVoice.class);

    @BeforeClass
    public void BeforeEcho() throws InterruptedException {

        LoginAction loginAction =new LoginAction(driver, WxVariable.LoginPage);

        loginAction.Login("xxxx","xxxx");

    }

    @Test(dataProvider="getData")
    public void testEcho(String action,String result) throws InterruptedException {

        HomeAction homeAction=new HomeAction(driver, WxVariable.TestPage);

        homeAction.wxSendKeys(action);

        assertEquals(homeAction.getLastResponse(),result) ;

        logger.info("Voice："+action+" "+"Echo："+result);

        Thread.sleep(3000);

    }

    @DataProvider(name = "getData")
    public static Object[][] getData() throws IOException {
        return getTestData(CsvData);
    }


    @AfterClass
    public void AfterEcho() {

    }


}
