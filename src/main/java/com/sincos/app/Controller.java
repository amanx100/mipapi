package com.sincos.app;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    HttpServer server = null;

    public Controller() {
        System.out.println("operation main controller!");
    }

    @FXML
    private void initialize() {
        System.out.println("init main fxml");
    }

    private Stage primaryStage = null;

    // Constructor Method
    public Controller(Stage primaryStage) throws ClassNotFoundException {

        this.primaryStage = primaryStage;
    }

    @FXML
    private void startButton(){
        System.out.println("Start Button Pressed!");

        try {
            server = HttpServerFactory.create("http://127.0.0.1:"+"7000"+"/");
            server.start();


            //statusView.setText("Status: Running");
            //LogTable.getDataList().add(new LogTableData("Server started"));

            //Resource9410.setModbusServiceMap(ModbusServiceMap.load());
            //buttonDisplayControler("start");

            System.out.println("REST Server Running");
        } catch (IOException e) {
            //multiClickFilter = true;
            //statusView.setText("Status: Error");
            //LogTable.getDataList().add(new LogTableData("Failed to start server! Assigned port is using by another application."));
            e.printStackTrace();
        }

    }

    @FXML
    private void stopButton(){
        System.out.println("Stop Button Pressed!");
    }


}
