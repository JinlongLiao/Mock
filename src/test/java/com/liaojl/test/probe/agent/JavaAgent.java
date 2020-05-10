package com.liaojl.test.probe.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
}
