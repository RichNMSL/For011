package com.run;

import com.dao.AllDao;
import com.util.DocUtil;
import com.vo.Accused;
import com.vo.Judgment;
import com.vo.Lawyer;

import java.io.File;
import java.util.*;

public class working_被告人_140 {

    public static void main(String[] args) {
        try {
            String targetPath = "C:\\Users\\Berry\\Desktop\\2017";
          //  String targetPath = "C:\\Users\\Berry\\Desktop\\aaa";

            //解析zip包


            //解析doc文件
            File docFile = new File(targetPath);
            File[] docList = docFile.listFiles();

            //判决书主体

            AllDao dao=new AllDao();

//            for (int i = 0; i < docList.length; i++) {
//                if (docList[i].isDirectory()) {
//                    String zipName = docList[i].getName();
//                    File lastFile = new File(targetPath + "\\" + zipName);
//                    File[] lsatList = lastFile.listFiles();
//                    for (int j = 0; j < lsatList.length; j++) {
//                        List<Accused> accusedList = new ArrayList<Accused>();
//                        List<Lawyer> lawyerList = new ArrayList<Lawyer>();
//                        Judgment judgment = new Judgment();
//                        judgment.setFileName(lsatList[j].getName());
//                        judgment.setZipName(zipName);
//                        String body = DocUtil.readFileContent(lsatList[j].getPath());
//                        String[] arr = body.split("</div>");
//
//                        for (int k = 0; k < arr.length; k++) {
//                            System.out.println(arr[k]);
//                            if(arr[k].trim().startsWith("原审被告人")){
//                                System.out.println("aa");
//                            }
//
//
//                                }
//
//
//                    }}
//
//            }
//            /////////////////////1
//            List<Judgment>need_compare=new ArrayList<Judgment>();
//            need_compare=dao.queryLawyersss( );
//            for (Judgment judgment : need_compare){
//                File file =new File(targetPath +"\\"+judgment.getZipName()+"\\"+judgment.getFileName());
//                String body = DocUtil.readFileContent(file.getPath());
//                if(body.contains("辩护人")){
//                    //更新为非委托
//                    dao.updateByid(judgment.getBranch());
//                   //System.out.println(judgment.getBranch());
//                }
//            }
//            //////////////////////素材程序
            List<Judgment>need_compare=new ArrayList<Judgment>();
            need_compare=dao.queryLawyersssaaa( );
            Set<Integer> set=null;
            int code=0;
            for (Judgment judgment : need_compare){
                File file =new File(targetPath +"\\"+judgment.getZipName()+"\\"+judgment.getFileName());
                String body = DocUtil.readFileContent(file.getPath());
                set=new TreeSet<Integer>();
                int a=body.lastIndexOf("简易程序");
                int b=body.lastIndexOf("普通程序");
                int c=body.lastIndexOf("速裁程序");
                set.add(a);
                set.add(b);
                set.add(c);
                Iterator iterator=set.iterator();
                while(iterator.hasNext()){
                   code=Integer.valueOf( iterator.next().toString());
                }

                if(code==a){
                    //dao.updateByidYAA(judgment.getBranch());
                    System.out.println(judgment.getBranch()+"a");
                }if(code==b){
                     //dao.updateByidYAA(judgment.getBranch());
                    System.out.println(judgment.getBranch()+"b");
                }
                if(code==c){
                     dao.updateByidYAA(judgment.getBranch());
                    System.out.println(judgment.getBranch()+"c");
                }
                    //更新为非委托

            }
//
////
////            //////////////////3
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
//
//             //////////////////4
//            List<Judgment>need_compare_new_re=new ArrayList<Judgment>();
//            need_compare_new=dao.queryLawyers_2( );
//
//            for (Judgment judgment : need_compare_new_re){
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
