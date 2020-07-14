package com.run;

import com.dao.AllDao;
import com.util.DocUtil;
import com.vo.Accused;
import com.vo.Judgment;
import com.vo.Lawyer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class working_step1 {

    public static void main(String[] args) {
        try {
            String targetPath = "C:\\Users\\Berry\\Desktop\\裁决书文档";
          //  String targetPath = "C:\\Users\\Berry\\Desktop\\aaa";

            //解析zip包


            //解析doc文件
            File docFile = new File(targetPath);
            File[] docList = docFile.listFiles();

            //判决书主体

            AllDao dao=new AllDao();

            for (int i = 0; i < docList.length; i++) {
                if (docList[i].isDirectory()) {
                    String zipName = docList[i].getName();
                    File lastFile = new File(targetPath + "\\" + zipName);
                    System.out.println("解析的zip包---------" + zipName);
                    File[] lsatList = lastFile.listFiles();
                    for (int j = 0; j < lsatList.length; j++) {
                        List<Accused>accusedList=new ArrayList<Accused>();
                        List<Lawyer>lawyerList=new ArrayList<Lawyer>();
                        Judgment judgment = new Judgment();
                        judgment.setFileName(lsatList[j].getName());
                        judgment.setZipName(zipName);
                        String body = DocUtil.readFileContent(lsatList[j].getPath());
                        if (body.startsWith("<body")) {
                            judgment.setTitle(body.substring(body.indexOf(">", 1) + 1, body.lastIndexOf("<")));
                        } else {
                            judgment = DocUtil.analysisJudgment(judgment, body);
                            accusedList = DocUtil.analysisAccused(body);


                        }
                             dao.insertJudgment(judgment);


                            for (Accused accused :accusedList){

                               dao.insertAccused(accused);
                            }


                    }





                }

            }
            /////////////////////1
            List<Judgment>need_compare=new ArrayList<Judgment>();
            need_compare=dao.queryLawyers( );
            for (Judgment judgment : need_compare){
                File file =new File(targetPath +"\\"+judgment.getZipName()+"\\"+judgment.getFileName());
                String body = DocUtil.readFileContent(file.getPath());
                if(body.contains("法律援助")){
                    //更新为非委托
                    dao.updateByid(judgment.getBranch());
                }
            }
            //////////////////////2
//            List<Judgment>need_compare_2=new ArrayList<Judgment>();
//            need_compare_2=dao.queryLawyers_2( );
//            for (Judgment judgment : need_compare_2){
//                File file =new File(targetPath +"\\"+judgment.getZipName()+"\\"+judgment.getFileName());
//                String body = DocUtil.readFileContent(file.getPath());
//                if(body.contains("法律援助")){
//                    //更新为非委托
//                    dao.updateByid(judgment.getBranch());
//                }
//            }


//
//            //////////////////3
//            List<Judgment>need_compare_new=new ArrayList<Judgment>();
//            need_compare_new=dao.queryLawyers_new( );
//
//            for (Judgment judgment : need_compare_new){
//                File file =new File(targetPath +"\\"+judgment.getZipName()+"\\"+judgment.getFileName());
//                String body = DocUtil.readFileContent(file.getPath());
//                if(body.contains("法律援助")){
//                    //需人工判断
//                    dao.updateByidLoss(judgment.getBranch());
//
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
