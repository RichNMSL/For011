package com.run;

import com.dao.AllDao;
import com.util.DocUtil;
import com.vo.Judgment;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class working_isEasy {
    public static void main(String[] args) throws SQLException {
        //需要重新判断的code集合
        //step1 只有一个律师且包含指定辩护人
        AllDao dao = new AllDao();
        List<Judgment> codeList = new ArrayList<Judgment>();
        codeList = dao.queryNeedBaoShan();
        String basePath = "C:\\Users\\Berry\\Desktop\\2017";



        for(Judgment judgment :codeList) {

            File file = new File(basePath + "\\" + judgment.getZipName() + "\\" + judgment.getFileName());
            String body = DocUtil.readFileContent(file.getPath());
            if (body.contains("指定的辩护人")){
                System.out.println(file.getPath());
            }


        }
    }

}
