package com.run;

import com.dao.AllDao;
import com.util.DocUtil;
import com.vo.Judgment;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class working_3 {
    public static void main(String[] args) throws SQLException {
        //需要重新判断的code集合
        AllDao dao = new AllDao();
        List<Judgment> codeList = new ArrayList<Judgment>();
        codeList = dao.queryNeedFixCode();
        String basePath = "C:\\Users\\Berry\\Desktop\\裁决书文档";


        for(Judgment judgment :codeList) {

            File file = new File(basePath + "\\" + judgment.getZipName() + "\\" + judgment.getFileName());
            String body = DocUtil.readFileContent(file.getPath());
            String[] arr = body.split("<div");
            for(int i=0;i<arr.length;i++){
                if(arr[i].contains("法律援助中心")&&!arr[i].substring(arr[i].lastIndexOf("'>") + 2).startsWith("辩护人")){
                    dao.updateBody( judgment.getCode(),arr[i].substring(arr[i].lastIndexOf("'>") + 2));
                }
            }

        }
        }
}
