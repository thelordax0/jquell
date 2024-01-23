package org.jquell;

import org.apache.commons.cli.*;
import org.fusesource.jansi.AnsiConsole;
import java.net.URL;
import static org.fusesource.jansi.Ansi.ansi;


public class App {
    public static void main(String[] args) {


        AnsiConsole.systemUninstall();

        System.out.println(ansi().eraseLine().reset());
        URL url=null;

        CommandLine cmd=null;

        // create Options object
        Options options = new Options();
        //create options
        //url option
        Option urlOpt = Option.builder("url")
                .argName("URL")
                .hasArg()
                .desc("connection url https://www.google.com/")
                .build();


        //alive option
        Option isAliveOpt = Option.builder("alive")
                .desc("controls if service alive")
                .build();
        //help option
        Option helpOpt = Option.builder("help")
                .desc("prints help")
                .build();
        //help option
        Option infoOpt = Option.builder("info")
                .desc("prints about server")
                .build();

        // add options
        options.addOption(urlOpt);
        options.addOption(isAliveOpt);
        options.addOption(helpOpt);
        options.addOption(infoOpt);


        CommandLineParser parser = new DefaultParser();



        if (args.length==0){

            //print help command
            Util.printHelp(options);
            System.exit(0);

        }



        //parse arguments
        try {
             cmd = parser.parse(options, args);

        } catch (Exception e) {

            System.out.println(ansi().fgBrightRed().a("Parse Error!Make sure you type the parameters correctly.").reset());
            System.exit(0);
        }





        //print help
        if(cmd.hasOption(helpOpt)){
            Util.printHelp(options);
            System.exit(0);
        }


        //-url <URL> -alive command
        if(cmd.hasOption(urlOpt) && cmd.hasOption(isAliveOpt)) {
           boolean isAlive=Util.isAlive(cmd.getOptionValue(urlOpt));
           if(isAlive) {
               System.out.println(ansi().fgBrightGreen().a("Server Alive").reset());
           }else {
               System.out.println(ansi().fgBrightRed().a("Server Close").reset());
           }
            System.exit(0);
        }


        if (cmd.hasOption(urlOpt) && cmd.hasOption(infoOpt)){
            try {
                url=new URL(cmd.getOptionValue(urlOpt));

                Util.info(url);
            }catch (Exception e){
                System.out.println(ansi().fgBrightYellow().a("URL must be http://www.google.com  format.").reset());
            }

            System.exit(0);
        }



        System.exit(0);
    }
}
