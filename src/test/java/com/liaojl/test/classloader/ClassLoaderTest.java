package com.liaojl.test.classloader;

import lombok.SneakyThrows;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import java.io.*;
import java.util.Arrays;

/**
 * @author LiaoJL
 * @description TODO
 * @file ClassLoaderTest.java
 * @email jinlongliao@foxmail.com
 * @date 2020/5/7 01:26
 */
public class ClassLoaderTest {
    private static final Logger log = LoggerFactory.getLogger(ClassLoaderTest.class);


    @Test
    public void read() throws IOException {
        File file = new File("target/test-classes/com/liaojl/test/classloader/MyClassImp.class");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileOutputStream outputStream = new FileOutputStream("MyClassImp.txt")) {
            byte[] cache = new byte[SIZE];
            int readSize;
            while ((readSize = fileInputStream.read(cache)) > -1) {
                if (readSize == SIZE) {
                    cache = Arrays.copyOf(cache, cache.length + SIZE);
                } else {
                    cache = Arrays.copyOf(cache, cache.length - SIZE + readSize);
                }
            }
            byte[] encode = Base64Utils.encode(cache);
            log.info("\n {}\n", new String(encode));
            outputStream.write(encode);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

    }
    public static final int SIZE = 2048;
    public synchronized void read2() throws IOException {
        File file = new File("target/test-classes/com/liaojl/test/classloader/MyClassImp.class");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileOutputStream outputStream = new FileOutputStream("MyClassImp.txt",true)) {
            byte[] cache = new byte[SIZE];
            int readSize;
            while ((readSize = fileInputStream.read(cache)) > -1) {
                     outputStream.write(cache,0,readSize);
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

    }

    @SneakyThrows
    @Test
    public void load() {
        MyTestClassLoader myClassLoader = new MyTestClassLoader();
        Class<?> aClass = myClassLoader.loadClass("com.liaojl.test.classloader.MyClassImp");
        MyClass myClass = (MyClass) aClass.getDeclaredConstructor().newInstance();
        log.info(myClass.tellMeSecret());
    }

}
