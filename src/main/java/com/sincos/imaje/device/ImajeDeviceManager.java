package com.sincos.imaje.device;

import com.sincos.app.GlobalData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ImajeDeviceManager {

    public static boolean isValidDevice() {
        boolean response = false;

        List<String> command = new ArrayList<>();
        List<String> finder = new ArrayList<>();
        String outputString;
        String finalOutputString = "";

        ///////////////////////////////////////////////////////////////////////////////////
        System.out.println("Ping the target device!");
        command.add("ping");
        command.add(GlobalData.getPrinterIp());

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // read the output from the command
            while ((outputString = stdInput.readLine()) != null)
            {
                finalOutputString += outputString+"\n";
            }

            // read any errors from the attempted command
            while ((outputString = stdError.readLine()) != null)
            {
                finalOutputString += outputString+"\n";
            }

            // to check the printer ip is available
            response = finalOutputString.contains("Reply from "+GlobalData.getPrinterIp()+": bytes=");

        } catch (IOException e) {
            e.printStackTrace();
        }

///////////////////////////////////////////////////////////////////////////////////////
        if (response) {
            command.clear();
            command.add("arp");
            command.add("-a");

            try {
                ProcessBuilder processBuilder = new ProcessBuilder(command);
                Process process = processBuilder.start();

                BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                // read the output from the command
                while ((outputString = stdInput.readLine()) != null)
                {
                    finder.add(outputString);
                    finalOutputString += outputString+"\n";
                }

                // read any errors from the attempted command
                while ((outputString = stdError.readLine()) != null)
                {
                    finder.add(outputString);
                    finalOutputString += outputString+"\n";
                }

                System.out.println(finalOutputString);
                response = false;
                for (int i=0; i<finder.size(); i++){
                    if (finder.get(i).contains(GlobalData.getDeviceMac()) && finder.get(i).contains(GlobalData.getPrinterIp())){
                        response = true;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
