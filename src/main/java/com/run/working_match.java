package com.run;

import com.dao.AllDao;
import com.util.DocUtil;
import com.vo.Accused;
import com.vo.Judgment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class working_match {

    public static void main(String[] args) {
        AllDao dao = new AllDao();
        List<Judgment> codeList = new ArrayList<Judgment>();
        String basePath = "C:\\Users\\Berry\\Desktop\\裁决书文档";
        int count = 0;
        int count1 = 0;

        try {
            codeList = dao.queryNeedBaoShan();
            for (Judgment judgment : codeList) {

                File file = new File(basePath + "\\" + judgment.getZipName() + "\\" + judgment.getFileName());
                String body = DocUtil.readFileContent(file.getPath());
                String[] arr = body.split("</div>");
                int start=0;
                int end=0;
                for(int i=0;i<arr.length;i++){
                    if(arr[i].contains("判决如下")){
                        start=i+1;
                    }
                    if(arr[i].contains("相关法律条文")){
                        end=i+1;
                    }
                }
                int num=0;

                for(int j=start;j<end;j++){
//                    if (arr[j].contains("有期徒刑")){
//                        System.out.println(arr[j]);
//
//                    }

                    //完全不包含有期徒刑
//                    if (!arr[j].contains("有期徒刑")){
//                        num++;
//                    }
                    //包含有期徒刑三年
                    if(  arr[j].contains("十年") &&!arr[j].contains("缓刑")&&arr[j].contains(judgment.getTitle().substring(0,6).trim())){
                        System.out.println(judgment.getTitle().replace("被告人暨附带民事公益诉讼","").substring(0,5) +arr[j]+file.getPath() ) ;
                       // System.out.println(file.getPath());
                    //  dao.updateyqtx(judgment.getBranch());
                    }
                }

//                if (num==end-start){
//                    System.out.println(file.getPath());
//                    dao.updateyqtx(judgment.getCode());
//                }



            }

            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
