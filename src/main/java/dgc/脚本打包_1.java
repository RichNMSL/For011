package dgc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 脚本打包_1 {

    public static void main(String[] args) {
        String basePath = "C:\\Users\\Berry\\Desktop\\scripts\\scripts";
        File file = new File(basePath);
        File[] flist = file.listFiles();
        for (File f : flist) {

            if (f.isDirectory()) {
                getDirectory(f);

            }

        }
    }

    static void getDirectory(File file) {

        File flist[] = file.listFiles();

        if (flist == null || flist.length == 0) {

        } else {
            for (int i = 0; i < flist.length; i++) {
                if (!flist[i].isDirectory()) {
                    System.out.println(flist[i].getPath());
                    System.out.println(flist[i].getParent()+"\\INIT_"+flist[i].getName());
                    flist[i].renameTo(new File(flist[i].getParent()+"\\INIT_"+flist[i].getName()));
                   // String a = readTXT(flist[i].getPath());

                } else {
                    getDirectory(flist[i]);
                }
            }
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
