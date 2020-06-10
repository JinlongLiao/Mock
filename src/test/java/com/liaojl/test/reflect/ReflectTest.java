package com.liaojl.test.reflect;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectTest {
    private static final Logger log = LoggerFactory.getLogger(ReflectTest.class);

    class A {
        public String a1;
        public String a2;
        public Object a3;
        protected String a4;
    }

    @Test
    public void modifierTest() {
        int aPublic = Modifier.PUBLIC;
        Field[] fields = A.class.getFields();
        for (Field field : fields) {
            log.info("class:{}->{}->modifier:{}", field.getClass(), field.getName(), field.getModifiers());
        }
    }
}
