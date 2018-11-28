package com.sincos.imaje.lib;

import java.io.IOException;
import java.util.List;

public class ComSPort implements Communication {
    public void connect() throws IOException {

    }

    public void disconnect() throws IOException {

    }

    public boolean isConnected() {
        return false;
    }

    public byte transmit(List<Byte> data) throws IOException {
        return 0;
    }

    public List<Byte> request(List<Byte> data) throws IOException {
        return null;
    }
}
