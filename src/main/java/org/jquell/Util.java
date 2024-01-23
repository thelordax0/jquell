package org.jquell;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static org.fusesource.jansi.Ansi.ansi;


public class Util {
    public static void printHelp(Options options){
        System.out.println(ansi().bgBrightYellow().fgBlack().a("-*-*-JQUELL-*-*-").reset());
        Collection<Option> optionList=options.getOptions();
        for (Option option:optionList) {
            if (option.getArgName()==null){
                System.out.println("-"+option.getOpt()+"    "+option.getDescription());
            }else {
                System.out.println("-"+option.getOpt()+"    "+"<"+option.getArgName()+">"+"    "+option.getDescription());
            }

        }

    }

    public static boolean isAlive(String url){
        try {
            URL _url = new URL(url);


            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            // Bağlantı türünü kontrol et
            int responseCode = connection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_UNAVAILABLE) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            System.out.println(ansi().fgBrightRed().a("URL must be http://www.google.com  format.").reset());
            System.exit(0);
        }
        return false;
    }




    public static void info(URL url){


        try {


            // HttpURLConnection oluşturun
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // GET isteği yapın
            connection.setRequestMethod("GET");

            // Yanıtın başlıklarını alın
            Map<String, List<String>> headers = connection.getHeaderFields();

            // Başlıkları ekrana yazdırın
            System.out.println("HTTP Response Headers:");
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }




            // Bağlantıyı kapatın
            connection.disconnect();



            InetAddress inetAddress = InetAddress.getByName(url.getHost());

            // Sunucunun IP adresini al
            System.out.println("IP: " + inetAddress.getHostAddress());


            // Protokol (http, https, ftp, vb.)
            System.out.println("Protocol: " + url.getProtocol());

            // Host adı
            System.out.println("Host: " + url.getHost());

            // Port numarası
            System.out.println("Port: " + url.getPort());

            // Yol (path)
            System.out.println("Path: " + url.getPath());

            // Sorgu parametreleri
            System.out.println("Query: " + url.getQuery());

            // Fragment (sayfanın içindeki bir konum)
            System.out.println("Fragment: " + url.getRef());

            // Dosya (URL'nin sonundaki dosya adı)
            System.out.println("File: " + url.getFile());

            // UserInfo (kullanıcı adı ve şifre)
            System.out.println("User Info: " + url.getUserInfo());
        } catch (Exception e) {
            System.err.println(ansi().fgBrightRed().a("Error:Invalid URL!").reset());
        }



    }
}
