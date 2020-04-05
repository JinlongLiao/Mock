package com.liaojl.io.bio.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * @author LiaoJL
 * @description TODO
 * @file TimeServerHandler.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/30 15:19
 */
public class TimeServerHandler extends Thread {
    private Socket socket;
    private static final Logger log = LoggerFactory.getLogger(TimeServerHandler.class);

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                System.out.printf("readLine: " + readLine);
                printWriter.println("FUCK\n");
                printWriter.flush();
            }
            System.out.printf("STOP");
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
