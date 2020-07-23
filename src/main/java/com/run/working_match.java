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
                List<Accused>accusedList=new ArrayList<Accused>();

                File file = new File(basePath + "\\" + judgment.getZipName() + "\\" + judgment.getFileName());
                String body = DocUtil.readFileContent(file.getPath());
                String[] arr = body.split("</div>");
                String name = null;
                String flag = null;



//                for (int i=0;i<arr.length;i++){
//                    if(arr[i].contains("简易程序")&&arr[i].contains("速裁程序")&&arr[i].lastIndexOf("简易程序")<arr[i].lastIndexOf("普通程序")&&!arr[i].contains("程序处罚决定书")){
//                        System.out.println(file.getPath());
//                        count++;
//                      //  dao.updatebyCodeNew(judgment.getCode());
//                        break;
//                    }
//                }

                if(body.contains("不宜适用普通程序")){
                   // dao.updatebyCodeNew(judgment.getCode());
                }
            }

            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
