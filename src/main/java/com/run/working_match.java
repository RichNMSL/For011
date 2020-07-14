package com.run;

import com.dao.AllDao;
import com.util.DocUtil;
import com.vo.Judgment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class working_match {

    public static void main(String[] args)  {
        AllDao dao = new AllDao();
        List<Judgment> codeList = new ArrayList<Judgment>();
        String basePath = "C:\\Users\\Berry\\Desktop\\裁决书文档";
        int count=0;
        try{
            codeList=dao.queryNeedBaoShan();
            for(Judgment judgment :codeList) {

                File file = new File(basePath + "\\" + judgment.getZipName() + "\\" + judgment.getFileName());
                String body = DocUtil.readFileContent(file.getPath());
                String[] arr = body.split("</div>");
                for (int i=0;i<arr.length;i++) {

                    if(arr[i].substring(arr[i].lastIndexOf("'>") + 2).startsWith("指定的辩护人")){
                        System.out.println(file.getPath());
                    }
                }



            }
            System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }





    }



    }
