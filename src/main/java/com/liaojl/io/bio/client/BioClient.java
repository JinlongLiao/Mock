package com.liaojl.io.bio.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * @author LiaoJL
 * @description TODO
 * @file BioClient.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/30 15:30
 */
public class BioClient {
    private static final Logger log = LoggerFactory.getLogger(BioClient.class);

    public static void main(String[] args) {
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("127.0.0.1", 9999);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                printWriter.println("Fuck You!!!");
                printWriter.flush();
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    System.out.printf("readLine: " + readLine + "\n");
                    break;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            ;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                    ;
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                    ;
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                    ;
                }
            }

        }
    }
}
