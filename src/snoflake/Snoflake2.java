package snoflake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Snoflake2 {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("D:\\data\\Keycsv.csv");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        PrintWriter pw = new PrintWriter("D:\\data\\Keycsvdatechange.csv");
        try (Scanner sc = new Scanner(new FileInputStream(f))) {

            sc.hasNextLine();
            pw.println(sc.nextLine());
            while(sc.hasNextLine()){

                String fullString = sc.nextLine();

                String[] s = fullString.split(",");
                if(s.length == 10) {

                    s[8] = getDate(s[8]);
                    s[9] = getDate(s[9]);
                    s[7] = getDate(s[7]);;
                }
                pw.println(String.join(",",s));
            }
            pw.flush();
            pw.close();

        } catch (FileNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    static String getDate(String strDate) throws ParseException {
        if(strDate.contains("/")){
            strDate = strDate.replace("/", "-");
        }
        strDate = strDate.trim();
        SimpleDateFormat dtf =new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sto =new SimpleDateFormat("yyyy-MM-dd");

        dtf.setLenient(false);

        Date d = dtf.parse(strDate);
        return sto.format(d);
       // return String.valueOf(d.getTime());
    }
}
