package com.run;

import com.dao.AllDao;
import com.util.DocUtil;
import com.vo.Judgment;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class working_step2 {
    public static void main(String[] args) throws SQLException {
        //需要重新判断的code集合
        AllDao dao = new AllDao();
        List<Judgment> codeList = new ArrayList<Judgment>();
        codeList = dao.queryNeedFix();
        String basePath = "C:\\Users\\Berry\\Desktop\\2017";

        for (Judgment judgment : codeList) {

            File file = new File(basePath + "\\" + judgment.getZipName() + "\\" + judgment.getFileName());
            String body = DocUtil.readFileContent(file.getPath());
            String[] arr = body.split("<div");

            //step1
//            int count = 0;
//            for (int i = 0; i < arr.length; i++) {
//                if (arr[i].contains("法律援助")) {
//                    count += 1;
//                }
//            }
//            int countNew = dao.queryInDB(judgment.getCode());
//            if (countNew == count) {
//                dao.updatebyCode(judgment.getCode());
//
//            }
            //step2
//            for (int i = 2; i < arr.length; i++) {
//                if (arr[i].contains("到庭")  && (arr[i].contains("指派的" + judgment.getTitle().substring(0, 5))
//                        || arr[i].contains("指派的" + judgment.getTitle().substring(3, 5)) ||  arr[i].contains("指定的" + judgment.getTitle().substring(3, 5)) ||
//                        arr[i].contains("指定的" + judgment.getTitle().substring(0, 5))||
//                        arr[i].contains("指定" + judgment.getTitle().substring(3, 5)) ||
//                        arr[i].contains("指定" + judgment.getTitle().substring(0, 5))||
//                        arr[i].contains("指定律师" + judgment.getTitle().substring(3, 5))||
//                        arr[i].contains("指定的律师" + judgment.getTitle().substring(3, 5))||
//                        arr[i].contains("指派律师" + judgment.getTitle().substring(3, 5))||
//                        arr[i].contains("指派的律师" + judgment.getTitle().substring(3, 5))
//
//
//                )) {
//                    System.out.println(judgment.getBranch());
//                    dao.updateByidN(judgment.getBranch());
//                }
//
//            }


            //step3
//            for (int i = 0; i < arr.length; i++) {
//                if ((arr[i].contains("到庭") ||arr[i].contains("出庭") ) && arr[i].contains("及其" + judgment.getTitle().substring(0, 5))) {
//                   // System.out.println(judgment.getBranch());
//                    dao.updateByidY(judgment.getBranch());
//                }
//            }
            //step4 判断全文出现的次数
//            int coco = 0;
//            for (int i = 0; i < arr.length; i++) {
//                if (arr[i].contains(judgment.getTitle().substring(3, 5))) {
//                    coco += 1;
//                }
//            }
//            if (coco == 1) {
//                dao.updateByidY(judgment.getBranch());
//            }
//            //判断是否在”法律援助之前出现“
//            for (int i = 2; i < arr.length; i++) {
//                if (arr[i].contains("法律援助中心") && arr[i].contains("人民检察院") &&arr[i].contains("刑诉")) {
//                    if (arr[i].indexOf("法律援助中心")>arr[i].indexOf( judgment.getTitle().substring(3, 5))){
//                        System.out.println(judgment.getBranch());
//                       // dao.updateByidY(judgment.getBranch());
//                    }
//                }
//
//            }

             //法律援助中心多个隔开
//                        for (int i = 2; i < arr.length; i++) {
//                if (arr[i].contains("法律援助中心") && arr[i].contains("人民检察院") &&arr[i].contains("刑诉")) {
//                    if (arr[i].indexOf("法律援助中心")<arr[i].indexOf( judgment.getTitle().substring(3, 5))){
//                        if(arr[i].contains("及辩护人" + judgment.getTitle().substring(3, 5))){
//                            System.out.println(judgment.getBranch());
//                          //  dao.updateByidY(judgment.getBranch());
//                        }
//                    }
//                }
//
//            }

            for (int i = 2; i < arr.length; i++) {
                if (arr[i].contains("人民检察院") &&arr[i].contains("刑诉")) {
                    if(arr[i].indexOf("法律援助中心" )< arr[i].indexOf( judgment.getTitle().substring(3, 5)  )&&
                            arr[i].lastIndexOf("辩护人" )< arr[i].indexOf( judgment.getTitle().substring(3, 5)  )){

                        if(arr[i].lastIndexOf("法律援助中心" ) >arr[i].lastIndexOf("及辩护人" )){
                            System.out.println( judgment.getBranch());
                            dao.updateByidN(judgment.getBranch());

                        }

                     }
                    }
                }
//



                        //多个隔开？？？？
//            for (int i = 2; i < arr.length; i++) {
//                if (arr[i].contains("法律援助中心") && arr[i].substring(arr[i].lastIndexOf("'>") + 2).startsWith("上海市") ) {
//                    if (arr[i].indexOf("法律援助中心")<arr[i].indexOf( judgment.getTitle().substring(3, 5))&&
//                            arr[i].indexOf("各自委托的辩护人")<arr[i].indexOf( judgment.getTitle().substring(3, 5) )){
//                        if(arr[i].contains("" + judgment.getTitle().substring(3, 5))){
//                            System.out.println(judgment.getBranch());
//                          //  dao.updateByidN(judgment.getBranch());
//                        }
//                    }
//                }
//
//            }
//            for (int i = 2; i < arr.length; i++) {
//                if (arr[i].contains("法律援助中心") && arr[i].substring(arr[i].lastIndexOf("'>") + 2).startsWith("崇明县") ) {
//                    if (arr[i].indexOf("法律援助中心")<arr[i].indexOf( judgment.getTitle().substring(3, 5))&&
//                            arr[i].indexOf("各自委托的辩护人")<arr[i].indexOf( judgment.getTitle().substring(3, 5) )){
//                        if(arr[i].contains("" + judgment.getTitle().substring(3, 5))){
//                            System.out.println(judgment.getBranch());
//                            //dao.updateByidN(judgment.getBranch());
//                        }
//                    }
//                }
//
//            }

        }
    }

}
