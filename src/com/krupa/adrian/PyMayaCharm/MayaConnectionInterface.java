package com.krupa.adrian.PyMayaCharm;

import com.krupa.adrian.PyMayaCharm.resources.Strings;

import java.io.*;
import java.net.Socket;
import java.text.MessageFormat;

public class MayaConnectionInterface {
    final private String host;
    final private int port;

    public MayaConnectionInterface(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connectToMaya() {
        try {
            createMayaLogFile();
            if (sendToMaya(Strings.CONNECT_MESSAGE)) {
                System.out.println("Connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sendToMaya(String message) {
        try {
            sendFileToMaya(createFile(message));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private File createFile(String message) throws IOException {
        File f = File.createTempFile("MayaCharmTemp", ".py");
        if (!f.exists()) {
            //noinspection ResultOfMethodCallIgnored
            f.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(message);
        bw.close();
        f.deleteOnExit();
        return f;
    }

    private void sendCodeToMaya(String message) throws IOException {
        try (Socket client = new Socket(host, port)) {

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println(message);
            out.close();
            System.out.println("Sending:\n" + message);
        }
    }

    public void sendFileToMaya(File file) throws IOException {
        sendCodeToMaya(MessageFormat.format(Strings.EXECFILE, file.getAbsolutePath().replace("\\", "/")));
    }

    private File createMayaLogFile() throws IOException {
        final File logFile = new File(Strings.LOG_FILE);
        if (!logFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            logFile.createNewFile();
        }
        return logFile;
    }
}
