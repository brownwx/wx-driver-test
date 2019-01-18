package com.wx.driver;

/**
 * @author wuxi
 * @date 2019年1月9日
 */
public enum DriverType {

    Firefox("firefox"), Chrome("chrome"),Safari("safari");

    private  String driver;

    //构造方法
    private DriverType(String driver) {

        this.driver = driver;

    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "DriverType{" +
                "driver='" + driver + '\'' +
                '}';
    }}


