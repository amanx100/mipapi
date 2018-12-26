package com.sincos.app;


import javafx.scene.text.Text;

public class GlobalData {
    // Set this value manually
    private static String deviceId = "00-90-9b-44-9d-71";

    // Status text object to change from anywhere
    public static Text viewStatus;

    private static String printerIp;
    private static String printerPort;
    private static String hostPort;
    private static boolean autoStartService;
    private static boolean autoMinimize;
    private static boolean exitOnClose;

    public static String getDeviceId() {
        return deviceId;
    }

    public static void setDeviceId(String deviceId) {
        GlobalData.deviceId = deviceId;
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

    public static boolean isAutoStartService() {
        return autoStartService;
    }

    public static void setAutoStartService(boolean autoStartService) {
        GlobalData.autoStartService = autoStartService;
    }

    public static boolean isAutoMinimize() {
        return autoMinimize;
    }

    public static void setAutoMinimize(boolean autoMinimize) {
        GlobalData.autoMinimize = autoMinimize;
    }

    public static boolean isExitOnClose() {
        return exitOnClose;
    }

    public static void setExitOnClose(boolean exitOnClose) {
        GlobalData.exitOnClose = exitOnClose;
    }
}
