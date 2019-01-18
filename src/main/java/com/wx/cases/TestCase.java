package com.wx.cases;

/**
 * @author wuxi
 * @date 2019年1月14日
 */
public class TestCase {

    private String beforeType;

    private String beforeDetail;

    private String language;

    private String action;

    private String expectedResult;

    private String actualResult;

    private String resultPass;

    public String getBeforeType() {
        return beforeType;
    }

    public void setBeforeType(String beforeType) {
        this.beforeType = beforeType;
    }

    public String getBeforeDetail() {
        return beforeDetail;
    }

    public void setBeforeDetail(String beforeDetail) {
        this.beforeDetail = beforeDetail;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public String getResultPass() {
        return resultPass;
    }

    public void setResultPass(String resultPass) {
        this.resultPass = resultPass;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "beforeType='" + beforeType + '\'' +
                ", beforeDetail='" + beforeDetail + '\'' +
                ", language='" + language + '\'' +
                ", action='" + action + '\'' +
                ", expectedResult='" + expectedResult + '\'' +
                ", actualResult='" + actualResult + '\'' +
                ", resultPass='" + resultPass + '\'' +
                '}';
    }
}
