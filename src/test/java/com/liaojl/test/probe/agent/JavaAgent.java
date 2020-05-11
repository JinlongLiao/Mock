package com.liaojl.test.probe.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import javassist.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class JavaAgent {
    private static final Logger log = LoggerFactory.getLogger(JavaAgent.class);

    @Test
    public void test() {
        // TODO Auto-generated method stub
        try {
            VirtualMachine virtualMachine = VirtualMachine.attach("41855");
            virtualMachine.loadAgent("/Users/liaojinlong/workspace/mywork/test/Mock/target/mock-1.0-SNAPSHOT.jar");
        } catch (AttachNotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AgentLoadException e) {
            e.printStackTrace();
        } catch (AgentInitializationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        log.info(String.valueOf(new TransClass().getNumber()));
    }

    @Test
    public void javassitTest() throws NotFoundException, CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        URL url = classPool.find("com.liaojl.test.probe.agent.TransClass");
        log.info("URL :{}", url);
        CtClass ctClass = classPool.get("com.liaojl.test.probe.agent.TransClass");
        CtMethod[] methods = ctClass.getMethods();
        for (CtMethod method : methods) {
            log.info("method：{}", method.getName());
        }
        CtMethod getNumber = ctClass.getDeclaredMethod("getNumber");
        getNumber.insertBefore("log.info(\"Javassit 新增\");");
        ctClass.toClass();
        TransClass transClass = new TransClass();
        String number = transClass.getNumber();
        String msg = String.valueOf(number);
        log.info(msg);
    }
}
