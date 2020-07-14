package com.util;


import com.vo.Accused;
import com.vo.Judgment;
import com.vo.Lawyer;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DocUtil {


    public static String readFileContent(String fileName) {
        String message = null;
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            if (sbf.toString().indexOf("<div") != -1) {
                message = sbf.toString().substring((sbf.toString().indexOf("<div", 1)), sbf.toString().lastIndexOf("</div>") + 6);
            } else {
                message = sbf.toString().substring((sbf.toString().indexOf("<body>", 1)), sbf.toString().lastIndexOf("</body>") + 7);
            }

            return message;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return message;
    }


    public static void readWord(String filePath) throws Exception {

        StringBuffer sbf = new StringBuffer();
        BufferedReader reader = null;

        HWPFDocument document = new HWPFDocument(new FileInputStream(filePath));

        try {

            List list = new ArrayList();
            ;
            Range range = document.getRange();

            CharacterRun run1 = null;//用来存储第一行内容的属性
            CharacterRun run2 = null;//用来存储第二行内容的属性
            int q = 1;
            for (int i = 0; i < range.numParagraphs() - 1; i++) {
                Paragraph para1 = range.getParagraph(i);// 获取第i段
                System.out.println(para1 + "----para1");
                Paragraph para2 = range.getParagraph(i + 1);// 获取第i段
                int t = i;              //记录当前分析的段落数

                String paratext1 = para1.text().trim().replaceAll("\r\n", "");   //当前段落和下一段
                String paratext2 = para2.text().trim().replaceAll("\r\n", "");
                run1 = para1.getCharacterRun(0);
                run2 = para2.getCharacterRun(0);
                if (paratext1.length() > 0 && paratext2.length() > 0) {
                    //这个if语句为的是去除大标题，连续三个段落字体大小递减就跳过
                    if (run1.getFontSize() > run2.getFontSize() && run2.getFontSize() > range.getParagraph(i + 2).getCharacterRun(0).getFontSize()) {
                        continue;
                    }
                    //连续两段字体格式不同
                    if (run1.getFontSize() > run2.getFontSize()) {

                        String content = paratext2;
                        run1 = run2;  //从新定位run1  run2
                        run2 = range.getParagraph(t + 2).getCharacterRun(0);
                        t = t + 1;
                        while (run1.getFontSize() == run2.getFontSize()) {
                            //连续的相同
                            content += range.getParagraph(t + 1).text().trim().replaceAll("\r\n", "");
                            run1 = run2;
                            run2 = range.getParagraph(t + 2).getCharacterRun(0);
                            t++;
                        }

                        if (paratext1.indexOf("HYPERLINK") == -1 && content.indexOf("HYPERLINK") == -1) {
                            System.out.println(q + "标题" + paratext1 + "\t内容" + content);
                            i = t;
                            q++;
                        }

                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }


    public static Judgment analysisJudgment(Judgment ju, String body) {
        String title = null;
        String code = null;
        String branch = null;
        String[] arr = body.split("</div>");

        if (!arr[2].contains("刑 事 判 决")) {
            //标题
            title = arr[0].substring(arr[0].lastIndexOf(">") + 1);
            ju.setTitle(title);

            //文书编号
            code = arr[2].substring(arr[2].lastIndexOf(">") + 1);
            ju.setCode(code);

            //公诉机关
            branch = arr[3].substring(arr[3].lastIndexOf(">") + 1);
            ju.setBranch(branch);

        } else {
            //标题
            title = arr[1].substring(arr[1].lastIndexOf(">") + 1);
            ju.setTitle(title);

            //文书编号
            code = arr[3].substring(arr[3].lastIndexOf(">") + 1);
            ju.setCode(code);

            //公诉机关
            branch = arr[4].substring(arr[4].lastIndexOf(">") + 1);
            ju.setBranch(branch);
        }
//是否简易程序
        //简易程序
        if ((body.contains("简易程序") && !body.contains("普通程序")) || body.contains("转为简易程序")) {
            ju.setIs_easy("1");
        }
        //普通程序
        if (!body.contains("程序") || body.contains("转为普通程序")) {
            ju.setIs_easy("2");
        }
        //速裁程序
        if (body.contains("适用速裁程序") && (body.contains("依法适用速裁程序") || body.contains("本院适用速裁程序"))) {
            ju.setIs_easy("3");
        }

        return ju;
    }

    public static List<Accused> analysisAccused(String body) {
        String code = null;
        String[] arr = body.split("</div>");
        List<Accused> accusedList = new ArrayList<Accused>();
        String name = null;
        String nextMessage = null;
        if (!arr[2].contains("刑 事 判 决")) {
            code = arr[2].substring(arr[2].lastIndexOf(">") + 1);
        } else {
            code = arr[3].substring(arr[3].lastIndexOf(">") + 1);

        }

        for (int i = 0; i < arr.length; i++) {

            if (is_accused(arr[i].substring(arr[i].lastIndexOf(">") + 1))) {
                Accused accused = new Accused();
                accused.setCode(code);
                name = arr[i].substring(arr[i].lastIndexOf(">") + 1);
                if (name.length() > 40) {
                    name = name.substring(0, 39);
                }
                accused.setName(name);
                nextMessage = arr[i + 1].substring(arr[i + 1].lastIndexOf(">") + 1);
                //判断这个被告有辩护人
                if (is_Lawyer(nextMessage)) {
                    accused.setIsLawyer("Y");
                    accused.setLawyerName(nextMessage);

                    if (nextMessage.contains("法律援助") || nextMessage.contains("指定")) {
                        accused.setIsEntrust("N");
                    } else {
                        accused.setIsEntrust("Y");
                    }

                }
                accusedList.add(accused);
            }
        }


        return accusedList;
    }


    public static List<Accused> analysisAccusedForBackUp(String body) {
        String code = null;
        String[] arr = body.split("</div>");
        List<Accused> accusedList = new ArrayList<Accused>();
        String name = null;
        code = arr[2].substring(arr[2].lastIndexOf("'>") + 2);
        for (int i = 0; i < arr.length && i < 10; i++) {
            if (arr[i].startsWith("被告人") && (arr[i].indexOf(",") < 9 || arr[i].indexOf("，") < 9)) {
                Accused accused = new Accused();
                accused.setCode(code);
                name = arr[i].substring(arr[i].lastIndexOf("'>") + 2);
                if (name.length() > 40) {
                    name = name.substring(0, 39);
                }
                accused.setName(name);
                accusedList.add(accused);
            }
        }


        return accusedList;
    }

    public static List<Lawyer> analysisLawyer(String body) {
        String code = null;
        String[] arr = body.split("</div>");
        List<Lawyer> lawyerArrayList = new ArrayList<Lawyer>();

        if (!arr[2].contains("刑 事 判 决")) {
            code = arr[2].substring(arr[2].lastIndexOf("'>") + 2);
        } else {
            code = arr[3].substring(arr[3].lastIndexOf("'>") + 2);

        }
        boolean flag = is_entrust(body);
        for (int i = 0; i < arr.length; i++) {

            if (is_Lawyer(arr[i].substring(arr[i].lastIndexOf("'>") + 2))) {
                if (arr[i].substring(arr[i].lastIndexOf("'>") + 2).contains("、")) {
                    String[] lawyerArr = arr[i].substring(arr[i].lastIndexOf("'>") + 2).split("、");
                    for (int t = 0; t < lawyerArr.length; t++) {
                        Lawyer lawyer = new Lawyer();
                        lawyer.setCode(code);
                        lawyer.setName(lawyerArr[t]);
                        if (flag) {
                            lawyer.setIs_entrust("Y");
                        } else {
                            lawyer.setIs_entrust("N");
                        }
                        lawyerArrayList.add(lawyer);
                    }
                } else {
                    Lawyer lawyer = new Lawyer();
                    lawyer.setCode(code);
                    lawyer.setName(arr[i].substring(arr[i].lastIndexOf("'>") + 2));
                    if (flag) {
                        lawyer.setIs_entrust("Y");
                    } else {
                        lawyer.setIs_entrust("N");
                    }
                    lawyerArrayList.add(lawyer);

                }

            }
        }


        return lawyerArrayList;
    }

    public static boolean is_accused(String message) {
        boolean flag = false;
        message = message.trim();


        if (message.startsWith("被告人") && (message.contains("国籍") || message.contains("护照") || message.contains("自报") || message.contains("，男")
                || message.contains(",女") || message.contains("，男") || message.contains("，女"))) {
            flag = true;

        } else if (message.startsWith("被告人") && message.indexOf("，") < message.indexOf(",") && message.indexOf("，") != -1 && message.indexOf(",") != -1) {
            String[] arr = message.split("，");
            if (arr.length > 1 && (arr[1].startsWith("男") || arr[1].startsWith("女") || arr[1].contains("化名") || arr[1].contains("曾用名") || arr[1].contains("外号") || arr[1].contains("护照"))) {
                flag = true;
            }
            if (arr[0].length() < 8) {
                flag = true;
            }

        } else if (message.startsWith("被告人") && message.indexOf(",") < message.indexOf("，") && message.indexOf(",") != -1 && message.indexOf("，") != -1) {
            String[] arr = message.split(",");
            if (arr.length > 1 && (arr[1].startsWith("男") || arr[1].startsWith("女") || arr[1].contains("化名") || arr[1].contains("曾用名") || arr[1].contains("外号") || arr[1].contains("护照"))) {
                flag = true;
            }
            if (arr[0].length() < 8) {
                flag = true;
            }
        } else if (message.startsWith("被告人") && message.indexOf(",") == -1 && message.indexOf("，") > 0) {
            String[] arr = message.split("，");
            if (arr.length > 1 && (arr[1].startsWith("男") || arr[1].startsWith("女") || arr[1].contains("化名") || arr[1].contains("曾用名") || arr[1].contains("外号") || arr[1].contains("护照"))) {
                flag = true;
            }
            if (arr[0].length() < 8) {
                flag = true;
            }
        } else if (message.startsWith("被告人") && message.indexOf("，") == -1 && message.indexOf(",") > 0) {
            String[] arr = message.split(",");
            if (arr.length > 1 && (arr[1].startsWith("男") || arr[1].startsWith("女") || arr[1].contains("化名") || arr[1].contains("曾用名") || arr[1].contains("外号") || arr[1].contains("护照"))) {
                flag = true;
            }
            if (arr[0].length() < 8) {
                flag = true;
            }
        } else if (message.startsWith("被告人") && message.length() < 60 && !message.contains("异议") && !message.contains("供认") && !message.contains("供述") && !message.contains("上述事实")) {
            flag = true;
        }

        return flag;
    }


    public static boolean is_Lawyer(String message) {
        message = message.trim();
        boolean flag = false;
        if ((message.startsWith("辩护人") || message.startsWith("指定辩护人")) && message.contains("律师")) {
            flag = true;
        }
        return flag;
    }


    public static boolean is_entrust(String message) {

        boolean flag = true;
        String[] arr = message.split("</div>");
        for (int i = 0; i < arr.length; i++) {
            while (arr[i].contains("法律援助")) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    public static boolean checkHasAccused(String body) {
        boolean flag = false;
        String[] arr = body.split("</div>");
        for (int i = 0; i < arr.length; i++) {
            while (arr[i].contains("被告人")) {
                flag = true;
                break;
            }
        }
        return flag;

    }

    public static boolean has_Entrust(String body, String target) {
        boolean flag = false;
        if (body.contains("法律援助中心指派的" + target.substring(0, 5))) {
            return true;
        }
        return flag;
    }


    public static void main(String[] args) {
        String a = "被告人王召(自报)，；因涉嫌盗窃犯罪于2018年9月27日被上海市。";

        System.out.println(is_accused(a));

    }
}