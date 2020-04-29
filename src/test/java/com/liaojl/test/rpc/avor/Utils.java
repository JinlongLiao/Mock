package com.liaojl.test.rpc.avor;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.avro.Protocol;

public class Utils {

    public static Protocol getProtocol() {
        Protocol protocol = null;
        try {
            URL url = Utils.class.getResource("/com/liaojl/test/rpc/avor/message.avpr");
            protocol = Protocol.parse(new File(url.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return protocol;
    }
}