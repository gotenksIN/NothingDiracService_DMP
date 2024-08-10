package com.nothing.dirac_DMP;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/* loaded from: classes4.dex */
public class IOUtils {
    public static String readFromStream(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            int len = in.read(buffer);
            if (len != -1) {
                out.write(buffer, 0, len);
            } else {
                String result = out.toString();
                in.close();
                out.close();
                return result;
            }
        }
    }

    public static void copyFile(InputStream in, FileOutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            int len = in.read(buffer);
            if (len != -1) {
                out.write(buffer, 0, len);
            } else {
                in.close();
                out.close();
                return;
            }
        }
    }

    public static String readFileBySingleCharStream(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String str = reader.readLine();
        return str;
    }

    public void writeFileByByteArray(File file, String str) throws IOException {
        OutputStream output = new FileOutputStream(file);
        byte[] data = str.getBytes();
        output.write(data);
        output.close();
    }

    public void writeFileByByte(File file, String str) throws IOException {
        OutputStream output = new FileOutputStream(file);
        byte[] data = str.getBytes();
        for (int i : data) {
            output.write(i);
        }
        output.close();
    }

    public void writeAppendFileByByteArray(File file, String str) throws IOException {
        OutputStream output = new FileOutputStream(file, true);
        byte[] data = str.getBytes();
        output.write(data);
        output.close();
    }

    public void readFileByByteArray(File file) throws IOException {
        InputStream input = new FileInputStream(file);
        byte[] data = new byte[1024];
        int len = input.read(data);
        input.close();
        System.out.println("銆�" + new String(data, 0, len) + "銆�");
    }

    public static String readFileByByte(File file) throws IOException {
        InputStream input = new FileInputStream(file);
        int foot = 0;
        byte[] data = new byte[1024];
        while (true) {
            int temp = input.read();
            if (temp != -1) {
                data[foot] = (byte) temp;
                foot++;
            } else {
                input.close();
                return new String(data, 0, foot);
            }
        }
    }

    static String readFile(String path) throws IOException {
        BufferedReader reader = null;
        try {
            StringBuilder output = new StringBuilder();
            reader = new BufferedReader(new FileReader(path));
            String newLine = "";
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                output.append(newLine).append(line);
                newLine = "\n";
            }
            String line2 = output.toString();
            try {
                reader.close();
            } catch (IOException e) {
            }
            return line2;
        } catch (Throwable th) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e2) {
                }
            }
            throw th;
        }
    }

    public void writeFileByChar(File file, String str) throws IOException {
        Writer out = new FileWriter(file);
        out.write(str);
        out.close();
    }

    public void readFileByChar(File file) throws IOException {
        Reader in = new FileReader(file);
        char[] data = new char[1024];
        int len = in.read(data);
        in.close();
        System.out.println("[" + new String(data, 0, len) + "]");
    }

    public Writer byteChangeToCharOutputStream(OutputStream out) {
        return new OutputStreamWriter(out);
    }

    public Reader byteChangtoCharInputStream(InputStream in) {
        return new InputStreamReader(in);
    }
}
