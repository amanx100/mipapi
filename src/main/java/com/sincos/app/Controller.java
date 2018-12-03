package com.sincos.app;

import com.sincos.imaje.device.ImajeDeviceManager;
import com.sincos.imaje.lib.ComENet;
import com.sincos.imaje.lib.Communication;
import com.sincos.imaje.lib.NetworkInterface9410;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class Controller {

    HttpServer server = null;

    @FXML
    Button startButton, stopButton;

    @FXML
    Text viewStatusMain;

    @FXML
    TextField printerIp, printerPort, hostPort;

    @FXML
    CheckBox autoStartService, autoMinimize, exitOnClose;

    private Stage primaryStage = null;

    // Constructor Method
    public Controller(Stage primaryStage) throws ClassNotFoundException {
        System.out.println("called Controller for main fxml!");
        this.primaryStage = primaryStage;
    }

    private void updateUiAndSettingsData() {
        System.out.println("Checking Database data manager");
        if (DataManager.makeDataManagerOkay()){
            // Reading all of the settings data and set it to settings data class
            Map<String, String> dbData = DataManager.getSettingsData();
            GlobalData.setPrinterIp(dbData.get("printerIp"));
            GlobalData.setPrinterPort(dbData.get("printerPort"));
            GlobalData.setHostPort(dbData.get("hostPort"));
            GlobalData.setAutoStartService(!dbData.get("autoStartService").equals("0"));
            GlobalData.setAutoMinimize(!dbData.get("autoMinimize").equals("0"));
            GlobalData.setExitOnClose(!dbData.get("exitOnClose").equals("0"));

            // Reading all of the settings data and set it to its own UI location
            printerIp.setText(GlobalData.getPrinterIp());
            printerPort.setText(GlobalData.getPrinterPort());
            hostPort.setText(GlobalData.getHostPort());
            autoStartService.setSelected(GlobalData.isAutoStartService());
            autoMinimize.setSelected(GlobalData.isAutoMinimize());
            exitOnClose.setSelected(GlobalData.isExitOnClose());
        } else {
            // Program may never come here
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("settings.dmp file is not present!\nPlease restart the application.");
            alert.showAndWait();
            Platform.exit();
            System.exit(0);
        }
    }

    @FXML
    private void initialize() {
        System.out.println("initializing main fxml");
        GlobalData.viewStatus = viewStatusMain;
        updateUiAndSettingsData();
        GlobalData.viewStatus.setText("Ready to run");
        stopButton.setDisable(true);

        if (GlobalData.isAutoStartService()){
            System.out.println("Auto service started");
            startButton();
        }
    }

    @FXML
    private void startButton(){
        System.out.println("Start Button Pressed!");
        startButton.setDisable(true);

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                GlobalData.viewStatus.setText("Finding printer...");
                if (ImajeDeviceManager.isValidDevice()){
                    try {
                        GlobalData.viewStatus.setText("API service starting...");
                        server = HttpServerFactory.create("http://127.0.0.1:"+hostPort.getText()+"/");
                        server.start();
                        stopButton.setDisable(false);
                        GlobalData.viewStatus.setText("API service running at port "+hostPort.getText());
                        System.out.println("REST Server Running at port "+hostPort.getText());
                    } catch (IOException e) {
                        startButton.setDisable(false);
                        GlobalData.viewStatus.setText("API host port error");
                        e.printStackTrace();
                    }
                } else {
                    startButton.setDisable(false);
                    GlobalData.viewStatus.setText("Printer not found");
                    System.out.println("License failed!");
                }

                return null;
            }
        };
        // Starting the task
        new Thread(task).start();
    }

    Communication imajeCommunication= new ComENet("192.168.10.220",2000,10,10);
    NetworkInterface9410 networkInterface9410 = new NetworkInterface9410(imajeCommunication);
    @FXML
    private void stopButton(){
        System.out.println("Stop Button Pressed!");
        stopButton.setDisable(true);
        startButton.setDisable(false);

        server.stop(0);
        GlobalData.viewStatus.setText("API service stopped");

    }

    @FXML
    private void saveButton() {
        System.out.println("Save button pressed!");
        boolean isValidPrinterIp = GlobalInputValidator.isValidIpAddress(printerIp.getText());
        boolean isValidPrinterPort = GlobalInputValidator.isValidHostPort(printerPort.getText());
        boolean isValidHostPort = GlobalInputValidator.isValidHostPort(hostPort.getText());

        if (isValidPrinterIp && isValidPrinterPort && isValidHostPort){
            DataManager.saveSettingsData(printerIp.getText(), Integer.parseInt(printerPort.getText()), Integer.parseInt(hostPort.getText()),autoStartService.isSelected(), autoMinimize.isSelected(), exitOnClose.isSelected());
            updateUiAndSettingsData();
            System.out.println("Data saved successfully");
            GlobalData.viewStatus.setText("Data saved");
        } else {
            System.out.println("cant save data due to invalid input");
            GlobalData.viewStatus.setText("Invalid settings data");
        }


    }
}
