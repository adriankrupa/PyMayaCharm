package com.krupa.adrian.PyMayaCharm;

import com.krupa.adrian.PyMayaCharm.resources.Strings;

import java.io.*;
import java.text.MessageFormat;

public class MayaConnectionInterface {

    public boolean sendToMaya(String message) {
        try {
            if(message.length() > 256) {
                sendFileToMaya(createFile(message));
            } else {
                sendCodeToMaya(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public File createFile(String message) throws IOException {
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
            PrintWriter out = ConnectionManager.INSTANCE.getWriter();
            out.println(message);
            System.out.println("Sending:\n" + message);
    }

    public void sendFileToMaya(File file) throws IOException {
        sendCodeToMaya(MessageFormat.format(Strings.EXECFILE, file.getAbsolutePath().replace("\\", "/")));
    }
}
