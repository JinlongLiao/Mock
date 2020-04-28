package com.liaojl.test.concurrent.x;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpTask {
    private static final Logger log = LoggerFactory.getLogger(HttpTask.class);

    public String doHttp(String url) throws IOException {
        // if (!url.startsWith("https")) throw new future.NotSecureProtocolException();

        long threadId = Thread.currentThread().getId();
        OkHttpClient client = new OkHttpClient();
        log.info("http io starts in thread id: " + threadId);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String result = response.headers().toString();


        // mock delay
        for (int i = 1; i <= 3; ++i) {
            // 阻塞本线程1秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) { /* do nothing*/ }

            log.info("delayed " + i + " seconds in thread id: " + threadId);
        }

        return "******** headers in thread id " + threadId + " ***********\n" + result + "***************** headers ******************";
    }
}