package dgc;

import com.sun.org.apache.xerces.internal.xs.StringList;
import com.util.DocUtil;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sdi_catch {

    private static final  Set<String> ETL=  new HashSet<String>();
    private static int index=  0;

    public static void main(String[] args) {

        String basePath = "C:\\Users\\Berry\\Desktop\\scripts\\scripts";
        File file=new File(basePath);
        File  [] flist= file.listFiles();
        ETL.add("");
        for (File f : flist) {

            if (f.isDirectory()) {
               // System.out.println("Dir==>" + f.getAbsolutePath());
                getDirectory(f);

            }

        }

       for (String a:ETL){
           if(a!=null&&a!=""){
               System.out.println(a.split("\\.")[1]);
           }

           }





    }


      static void getDirectory(File file) {

        File flist[] = file.listFiles();

        if (flist == null || flist.length == 0) {

        }else{
            for (int i=0;i<flist.length;i++) {
                if (!flist[i].isDirectory()) {
                    String a = readTXT(flist[i].getPath());
                    String find = "sdi_[A-Z,a-z,0-9,.,_]*";
                    Pattern p = Pattern.compile(find);
                    Matcher matcher = p.matcher(a);
                    while (matcher.find()) {
                        if(matcher.group().contains(".")){
                            String b=matcher.group();
                            ETL.add(b);
                          // System.out.println(b);
                        }
                       // System.out.println(matcher.group());
                    }
                }else{
                    getDirectory(flist[i]);
                }
            }

//            if (!file.isDirectory()){
//                //System.out.println(file.getPath());
//          String a=readTXT(file.getPath());
//          String find="sdi_[A-Z,a-z,0-9,.,_]*";
//          Pattern p = Pattern.compile(find);
//          Matcher matcher = p.matcher(a);
//         while(matcher.find()) {
//             ETL.add(matcher.group());
//             System.out.println(matcher.group());
//         }
//
//            }
        }


    }


     static String readTXT(String file) {

        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            String message = sbf.toString();
            return message;
        } catch (Exception E){
            E.printStackTrace();
            return  null;
        }

    }



    }





