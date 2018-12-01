package com.sincos.app;


import javafx.scene.text.Text;

public class GlobalData {
    // Set this value manually
    private static String deviceMac = "00-90-9b-44-9d-71";

    // Status text object to change from anywhere
    public static Text viewStatus;

    private static String printerIp;
    private static String printerPort;
    private static String hostPort;
    private static String autoStartService;
    private static String autoMinimize;
    private static String exitOnClose;

    public static String getDeviceMac() {
        return deviceMac;
    }

    public static String getPrinterIp() {
        return printerIp;
    }

    public static void setPrinterIp(String printerIp) {
        GlobalData.printerIp = printerIp;
    }

    public static String getPrinterPort() {
        return printerPort;
    }

    public static void setPrinterPort(String printerPort) {
        GlobalData.printerPort = printerPort;
    }

    public static String getHostPort() {
        return hostPort;
    }

    public static void setHostPort(String hostPort) {
        GlobalData.hostPort = hostPort;
    }

    public static String getAutoStartService() {
        return autoStartService;
    }

    public static void setAutoStartService(String autoStartService) {
        GlobalData.autoStartService = autoStartService;
    }

    public static String getAutoMinimize() {
        return autoMinimize;
    }

    public static void setAutoMinimize(String autoMinimize) {
        GlobalData.autoMinimize = autoMinimize;
    }

    public static String getExitOnClose() {
        return exitOnClose;
    }

    public static void setExitOnClose(String exitOnClose) {
        GlobalData.exitOnClose = exitOnClose;
    }
}
