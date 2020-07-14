package com.run;

import com.dao.AllDao;
import com.util.DocUtil;
import com.vo.Judgment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class working_baoshan_111 {

    public static void main(String[] args)  {
        AllDao dao = new AllDao();
        List<Judgment> codeList = new ArrayList<Judgment>();
        String basePath = "C:\\Users\\Berry\\Desktop\\裁决书文档";

        try{
            codeList=dao.queryNeedNMSL();
            for(Judgment judgment :codeList) {

                File file = new File(basePath + "\\" + judgment.getZipName() + "\\" + judgment.getFileName());
                String body = DocUtil.readFileContent(file.getPath());
                String[] arr = body.split("</div>");
                for (int i=0;i<arr.length;i++){


                    if( arr[i].substring(arr[i].lastIndexOf("'>") + 2).startsWith(judgment.getBranch())){


                        if(arr[i+1].substring(arr[i+1].lastIndexOf("'>") + 2).startsWith("指定辩护人")){
                            //System.out.println(judgment.getTitle());
                           dao.updateInfor(judgment.getTitle(),arr[i+1].substring(arr[i+1].lastIndexOf("'>") + 2).substring(0,10));

                        }
                    }

                }


            }

        }catch (Exception e){
            e.printStackTrace();
        }





    }



    }
