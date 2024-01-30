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


            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        
            connection.setRequestMethod("GET");

          
            Map<String, List<String>> headers = connection.getHeaderFields();

           
            System.out.println("HTTP Response Headers:");
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }




          
            connection.disconnect();



            InetAddress inetAddress = InetAddress.getByName(url.getHost());

           
            System.out.println("IP: " + inetAddress.getHostAddress());


          
            System.out.println("Protocol: " + url.getProtocol());


            System.out.println("Host: " + url.getHost());

           
            System.out.println("Port: " + url.getPort());

            
            System.out.println("Path: " + url.getPath());

          
            System.out.println("Query: " + url.getQuery());

            
            System.out.println("Fragment: " + url.getRef());

        
            System.out.println("File: " + url.getFile());

          
            System.out.println("User Info: " + url.getUserInfo());
        } catch (Exception e) {
            System.err.println(ansi().fgBrightRed().a("Error:Invalid URL!").reset());
        }



    }
}
