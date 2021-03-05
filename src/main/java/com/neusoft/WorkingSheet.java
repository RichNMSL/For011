package com.neusoft;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.print.DocFlavor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkingSheet {

    public static void main(String[] args) throws SQLException {

        //
        String excelFileName="C:\\Users\\Berry\\Desktop\\验证\\qqq.xlsx";
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<String> list = null;
        String gongdan = null;
        String message=null;
        NeuDao dao=new NeuDao();
        wb = ExcelUtil.readExcel(excelFileName);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = 5;
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
//                    for (int j=0;j<colnum;j++){
//                        cellData = row.getCell(j).toString();
//                        System.out.println(cellData);
//                    }
                    gongdan = row.getCell(0).toString();
                    message = row.getCell(4).toString();
                    System.out.println(gongdan+message);
                    dao.insertTable(gongdan,message);

                }else{
                    break;
                }
            }
        }
    }
}
