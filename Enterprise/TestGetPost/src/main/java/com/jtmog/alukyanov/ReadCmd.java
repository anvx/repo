package com.jtmog.alukyanov;

import com.jtmog.alukyanov.task1.RequestWithURL;
import com.jtmog.alukyanov.task2.RequestWithHTTP;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Scanner;

public class ReadCmd {
    private static boolean urlRequest = false;
    private static boolean httpRequest = false;

    public static void main(String[] args) throws IOException {
        System.out.println(
                "Press \"1\" to use request throw URL(URLConnection)\n"
                        + "      \"2\" to use request throw HTTP");

        Scanner scan = new Scanner(System.in);
        int methodIndex = scan.nextInt();

        if (methodIndex == 1) {
            urlRequest = true;
        } else if (methodIndex == 2) {
            httpRequest = true;
        } else {
            System.err.println("Please choose correct method.");
            main(args);
        }
        getInputCommandLine(args);
    }

    private static boolean getInputCommandLine(String[] args) throws IOException {
        Options options = setOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("use GET or POST", options);
            System.exit(1);
        }

        String id;
        if ((id = cmd.getOptionValue("GET")) != null) {
            if (urlRequest) {
                RequestWithURL.doGet(id);
            } else {
                RequestWithHTTP.doGet(id);
            }
            return true;
        } else if ((id = cmd.getOptionValue("POST")) != null) {
            if (urlRequest) {
                RequestWithURL.doPost(id);
            } else {
                RequestWithHTTP.doPost(id);
            }
            return true;
        }
        return false;
    }

    private static Options setOptions() {
        Options options = new Options();

        OptionGroup requiredOptions = new OptionGroup();
        requiredOptions.setRequired(true);

        Option optionGet = new Option("GET", true, "uses to GET from JSON");
        optionGet.setRequired(true);
        requiredOptions.addOption(optionGet);


        Option optionPost = new Option("POST", true, "uses to POST to JSON");
        optionPost.setRequired(true);
        requiredOptions.addOption(optionPost);

        options.addOptionGroup(requiredOptions);
        return options;
    }
}
