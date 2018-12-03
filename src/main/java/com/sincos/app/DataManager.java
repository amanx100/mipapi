package com.sincos.app;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

    // providing the existing database connection
    // if database file is not available then create new one and immediately provide the new db connection
    private static Connection getDbConnection() throws SQLException{
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            //DB will create automatically if not exists
            connection = DriverManager.getConnection("jdbc:sqlite:settings.dmp");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    // To check the data table is available or not.
    private static  boolean isTableAvailable(String tableName) {

        boolean returnStatus = false;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDbConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='"+tableName+"'");

            if (resultSet.next()) {
                System.out.println("Table exists");
                returnStatus = true;
            } else {
                System.out.println("Table not exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return returnStatus;
    }

    // method to execute the sql which result is boolean tye and do not have result set
    // ie. like creating a new table
    private static boolean statementExecute(String sql) {
        boolean returnStatus = false;

        Connection connection = null;
        Statement statement = null;

        try {
            connection = getDbConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            returnStatus = true;
        } catch (SQLException e) {
            returnStatus = false;
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                returnStatus = false;
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                returnStatus = false;
                e.printStackTrace();
            }
        }

        return returnStatus;
    }

    // method to check the application required data tables and create new one if not available
    // all of the database application table creation operation is here
    public static boolean makeDataManagerOkay() {

            boolean returnStatus = false;
            boolean returnStatus_1 = false;
            boolean returnStatus_2 = false;

            if (isTableAvailable("settings")){
                System.out.println("settings table already available. do not need create one.");
                returnStatus = true;
            } else {
                System.out.println("settings table is not available. creating new one.");
                returnStatus_1 = statementExecute("CREATE TABLE settings(id INTEGER PRIMARY KEY, printerIp VARCHAR(20), printerPort INT, hostPort INT, autoStartService INT, autoMinimize INT, exitOnClose INT)");
                returnStatus_2 = statementExecute("INSERT INTO settings VALUES (null, '192.168.10.220', 2000, 4000, 0, 0, 0)");
                // if all return status true then the final return status true
                returnStatus = (returnStatus_1 && returnStatus_2);
            }

        return returnStatus;
    }

    public static Map<String,String> getSettingsData() {


        Map<String, String> returnData = new HashMap<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDbConnection();
            statement = connection.createStatement();

            System.out.println("Settings data fetching!");
            resultSet = statement.executeQuery("SELECT * FROM settings WHERE id = 1");
            if (resultSet.next()) {
                returnData.put("printerIp", resultSet.getString("printerIp"));
                returnData.put("printerPort", resultSet.getString("printerPort"));
                returnData.put("hostPort", resultSet.getString("hostPort"));
                returnData.put("autoStartService", resultSet.getString("autoStartService"));
                returnData.put("autoMinimize", resultSet.getString("autoMinimize"));
                returnData.put("exitOnClose", resultSet.getString("exitOnClose"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return returnData;
    }

    public static boolean saveSettingsData(String printerIp, int printerPort, int hostPort, boolean autoStartService, boolean autoMinimize, boolean exitOnClose) {
        boolean returnStatus;

        int _autoStartService = autoStartService?1:0;
        int _autoMinimize = autoMinimize?1:0;
        int _exitOnClose = exitOnClose?1:0;

        returnStatus = statementExecute("UPDATE settings SET printerIp='"+printerIp+"', printerPort="+printerPort+", hostPort="+hostPort+", autoStartService="+_autoStartService+", autoMinimize="+_autoMinimize+", exitOnClose="+_exitOnClose+" WHERE id=1");
        return returnStatus;
    }

}
