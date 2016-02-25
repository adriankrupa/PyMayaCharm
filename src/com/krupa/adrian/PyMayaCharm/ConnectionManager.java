package com.krupa.adrian.PyMayaCharm;

import java.io.PrintWriter;
import java.net.Socket;

public final class ConnectionManager {

    public static ConnectionManager INSTANCE = new ConnectionManager();
    private Socket pythonSocket = null;
    private PrintWriter writer = null;

    private ConnectionManager() {
    }

    public Socket getPythonSocket() {
        if (pythonSocket == null) {
            try {
                pythonSocket = new Socket("localhost", 6002);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pythonSocket;
    }

    public PrintWriter getWriter() {
        if(writer == null) {
            try {
                writer = new PrintWriter(getPythonSocket().getOutputStream(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return writer;
    }
}
