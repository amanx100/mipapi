package com.sincos.imaje.lib;

import java.io.IOException;
import java.util.List;

public interface Communication {
    public void connect() throws IOException;
    public void disconnect() throws IOException;
    public boolean isConnected();
    public byte transmit(List<Byte> data) throws IOException;
    public List<Byte> request(List<Byte> data) throws IOException;
}
