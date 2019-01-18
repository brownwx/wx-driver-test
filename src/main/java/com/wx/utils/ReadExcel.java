package com.wx.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.wx.cases.TestCase;
import com.wx.variable.WxVariable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author wuxi
 * @date 2019年1月14日
 */
public class ReadExcel {

    public static List<TestCase> readXls() throws IOException {
        InputStream is = new FileInputStream(WxVariable.ExcelData);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        TestCase testCase = null;
        List<TestCase> list = new ArrayList<TestCase>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    testCase = new TestCase();
                    HSSFCell beforeType = hssfRow.getCell(0);
                    HSSFCell beforeDetail = hssfRow.getCell(1);
                    HSSFCell language = hssfRow.getCell(2);
                    HSSFCell action = hssfRow.getCell(3);
                    HSSFCell expectedResult = hssfRow.getCell(4);

                    testCase.setBeforeType(String.valueOf(beforeType));
                    testCase.setBeforeDetail(String.valueOf(beforeDetail));
                    testCase.setLanguage(String.valueOf(language));
                    testCase.setAction(String.valueOf(action));
                    testCase.setExpectedResult(String.valueOf(expectedResult));

                    list.add(testCase);

                }
            }
        }
        return list;
    }

    public static Iterator<Object[]> Provider() throws IOException {

        List<Object[]> dataProvider = new ArrayList<Object[]>();

        //数据库
        //String sql = "select * from cases";
        //List<TestCase> testCase = DbUtils.casesAll(sql, DbUtils.getConn());

        List<TestCase> cases = readXls();
        // 1 使用迭代器遍历list
        Iterator<TestCase> list = cases.iterator();
        while (list.hasNext()) {
            dataProvider.add(new Object[] { list.next() });
        }

        // 2 使用for循环遍历
        // for (Cases TestCase : cases) {
        // dataProvider.add(new Object[] { TestCase });
        // }

        return dataProvider.iterator();
    }

}
