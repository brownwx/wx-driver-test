package com.wx.listen;

import com.wx.base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import java.util.Date;
import java.util.Iterator;

public class TestListener extends TestListenerAdapter {

	private static Logger logger = LoggerFactory.getLogger(TestListener.class);

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info(tr.getName() + " Failure");

		//tr.getName 当前测试的方法名，tr.getInstance() 获取当前测试的实例
		BaseTest tb = (BaseTest) tr.getInstance();

		String imageUrl=tr.getName()+"-" + (new Date().getTime()) +"-"+Thread.currentThread().getId();

		try {
			tb.getDriver().takeScreen(System.getProperty("user.dir")+"/target/",
					imageUrl);

			Reporter.log("<img src="+imageUrl+ ".png" + " style=width:800px;height:500px img/>", true);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info(tr.getName() + " Skipped");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info(tr.getName() + " Success");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(tr.getName() + " Start");
	}
	

	@Override
	public void onFinish(ITestContext testContext) {
//		super.onFinish(testContext);
//		Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
//		while (listOfFailedTests.hasNext()) {
//			ITestResult failedTest = (ITestResult) listOfFailedTests.next();
//			ITestNGMethod method = failedTest.getMethod();
//			if (testContext.getFailedTests().getResults(method).size() > 1) {
//				listOfFailedTests.remove();
//			} else {
//				if (testContext.getPassedTests().getResults(method).size() > 0) {
//					listOfFailedTests.remove();
//				}
//
//			}
//		}
	}

}