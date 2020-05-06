package com.liaojl.test.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class MyTestClassLoader extends ClassLoader {
    private int SIZE = 2048;
    private static final Logger log = LoggerFactory.getLogger(MyTestClassLoader.class);

    @Override
    protected Class<?> findClass(String name) {
        File file = new File("MyClassImp.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] cache = new byte[SIZE];
            int readSize;
            while ((readSize = fileInputStream.read(cache)) > -1) {
                if (readSize == SIZE) {
                    cache = Arrays.copyOf(cache, cache.length + SIZE);
                } else {
                    cache = Arrays.copyOf(cache, cache.length - SIZE + readSize);
                }
            }
            byte[] decode = Base64Utils.decode(cache);
            Class<?> aClass = defineClass(name, decode, 0, decode.length);
            return aClass;
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
