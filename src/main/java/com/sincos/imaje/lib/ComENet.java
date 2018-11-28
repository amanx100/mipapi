package com.sincos.imaje.lib;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ComENet implements Communication {

    private String host;
    private int port;
    private int connectionTimeout;
    private int responseTimeout;

    Socket connection = null;

    public ComENet(String host, int port, int connectionTimeout, int responseTimeout) {
        this.host = host;
        this.port = port;
        this.connectionTimeout = connectionTimeout;
        this.responseTimeout = responseTimeout;
    }

    public void connect() throws IOException {
        connection = new Socket( host, port);
    }

    public void disconnect() throws IOException {
        connection.close();
    }

    public boolean isConnected() {
        return connection.isConnected();
    }

    public byte transmit(List<Byte> data) throws IOException {
        // to transmit the data
        for (int i=0; i<data.size(); i++) {
            connection.getOutputStream().write(data.get(i));
        }
        // to receive the reply from the device
        return (byte)connection.getInputStream().read();
    }

    public List<Byte> request(List<Byte> data) throws IOException {
        int dataLength = 0;
        int dl_1 = 0;
        int dl_2 = 0;
        List<Byte> arrayList = new ArrayList<Byte>();
        // to transmit the data
        for (int i=0; i<data.size(); i++) {
            connection.getOutputStream().write(data.get(i));
        }
        // to receive the reply from the device
        connection.getInputStream().read(); // ack
        connection.getInputStream().read(); // function Code
        dl_1 = connection.getInputStream().read(); // dl_1
        dl_2 = connection.getInputStream().read(); // dl_2
        dataLength = ((dl_1 & 0x007F)<<8) | (dl_2 & 0x00FF);

        for(int i=0; i<dataLength; i++){
            arrayList.add((byte)connection.getInputStream().read());
        }
        connection.getInputStream().read(); // crc
        connection.getOutputStream().write(0x06); // Back acknowledgement
        return arrayList;
    }
}
