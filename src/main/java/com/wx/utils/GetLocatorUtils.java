package com.wx.utils;

import org.openqa.selenium.By;


public class GetLocatorUtils {
	
		ProUtil locator;
		
		public GetLocatorUtils(String locatorFile){
			
			this.locator = new ProUtil(locatorFile);

		}
	/**
	 * 获取元素属性信息及对应数据值
	 * @param locatorType
	 * @return
	 * @throws Exception
	 */
	public By getByLocalor(String locatorType) {

		// 将配置对象中的定位类型存到 locatorType 变量，将定位表达式的值存入到 locatorValue 变量
		String locatorKeyValue = locator.getKey(locatorType);

		String type = locatorKeyValue.split(">")[0];// name
		String value = locatorKeyValue.split(">")[1];// 登录
		// 输出 locatorType 变量值和locatorValue 变量值，验证是否赋值正确

		// 根据 locatorType 的变量值内容判断，返回何种定位方式的 By 对象
		if (type.equalsIgnoreCase("id"))
			return By.id(value);
		else if (type.equalsIgnoreCase("xpath"))
			return By.xpath(value);
		else if ((type.toLowerCase().equalsIgnoreCase("classname")))
			return By.className(value);
		else if ((type.equalsIgnoreCase("linkText")))
			return By.linkText(value);
		else if (type.equalsIgnoreCase("partiallinktext"))
			return By.partialLinkText(value);
		else if ((type.equalsIgnoreCase("cssselector")))
			return By.cssSelector(value);
		else{
			throw new RuntimeException(" locator type not exist in this framework：" + locatorType);
		}
	}
	
	/**
	 * 获取元素属性对应数据值
	 * @param by
	 * @return
	 * @throws Exception
	 */
	public String getLocalor(String by) {
		String locatorKeyValue = locator.getKey(by);
		// 将配置对象中的定位类型存到 locatorType 变量，将定位表达式的值存入到 locatorValue 变量
		String type = locatorKeyValue.split(">")[0];// name
		String value = locatorKeyValue.split(">")[1];// 登录
		// 输出 locatorType 变量值和locatorValue 变量值，验证是否赋值正确

		return value;
	}

}
