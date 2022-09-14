package dgc;

import org.json.JSONObject;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 脚本打包2 {

    public static void main(String[] args)  {
        String basePath = "C:\\Users\\Berry\\Desktop\\scripts\\scripts";
        File file = new File(basePath);
        File[] flist = file.listFiles();
        for (File f : flist) {

            if (f.isDirectory()) {
                getDirectory(f);

            }

        }
    }

    static void getDirectory(File file)  {
        try{
            File flist[] = file.listFiles();
            String aaa=null;
            if (flist == null || flist.length == 0) {

            } else {
                for (int i = 0; i < flist.length; i++) {
                    if (!flist[i].isDirectory()) {
                        String a = readTXT(flist[i].getPath());
                        JSONObject json=new JSONObject(a);
                        String path=json.get("directory").toString();
                        String name=json.get("name").toString();
                       aaa= json.toString().replace("\"],\"description\":","\",\"description\":").
                                replace("\"name\":[\"","\"name\":\"").

                        replace("\"directory\":[\"","\"directory\":\"").
                        replace("\"],\"content\":\"","\",\"content\":\"");
                        FileWriter fileWriter =new FileWriter(flist[i]);
                        fileWriter.write("");
                        fileWriter.flush();
                        fileWriter.write(aaa);
                        fileWriter.flush();
                        fileWriter.close();

                        //System.out.println(json.get("directory"));

                    } else {
                        getDirectory(flist[i]);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
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
        } catch (Exception E) {
            E.printStackTrace();
            return null;
        }

    }

}
