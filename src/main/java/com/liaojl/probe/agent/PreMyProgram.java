package com.liaojl.probe.agent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * @author LiaoJL
 * @description TODO <code>java -javaagent:jar文件的位置 [= 传入 premain 的参数 ]</code>
 * 该方法在main方法之前运行，与main方法运行在同一个JVM中
 * 并被同一个System ClassLoader装载
 * 被统一的安全策略(security policy)和上下文(context)管理
 * @file PreMyProgram.java
 * @email jinlongliao@foxmail.com
 * @date 2020/5/10 21:20
 */
public class PreMyProgram {
    private static final Logger log = LoggerFactory.getLogger(PreMyProgram.class);

    /**
     * 是在主程序运行后再执行，意味着我们可以获取主程序运行时的信息，
     * 这里我们打印出来主程序中加载的类名。
     *
     * @param agentOps agentArgs 是 premain 函数得到的程序参数，
     *                 随同 “-javaagent”一起传入。
     *                 与 main 函数不同的是，这个参数是一个字符串而不是一个字符串数组，
     *                 如果程序参数有多个，程序将自行解析这个字符串。
     * @param inst     Instrumentation 的最大作用，就是类定义动态改变和操作。
     *                 在 Java SE 5 及其后续版本当中
     *                 ，开发者可以在一个普通 Java 程序（带有 main 函数的 Java 类）运行时，
     *                 通过 -javaagent参数指定一个特定的 jar 文件（包含 Instrumentation 代理）
     *                 来启动 Instrumentation 的代理程序。
     */
    public static void agentmain(String agentOps, Instrumentation inst) {
        log.info("====agentmain 方法执行");
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class allLoadedClass : allLoadedClasses) {
            log.info("{}", allLoadedClasses.getClass().getName());
        }
        log.info(agentOps);
    }

    /**
     * @param agentOps agentArgs 是 premain 函数得到的程序参数，
     *                 随同 “-javaagent”一起传入。
     *                 与 main 函数不同的是，这个参数是一个字符串而不是一个字符串数组，
     *                 如果程序参数有多个，程序将自行解析这个字符串。
     * @param inst     Instrumentation 的最大作用，就是类定义动态改变和操作。
     *                 在 Java SE 5 及其后续版本当中
     *                 ，开发者可以在一个普通 Java 程序（带有 main 函数的 Java 类）运行时，
     *                 通过 -javaagent参数指定一个特定的 jar 文件（包含 Instrumentation 代理）
     *                 来启动 Instrumentation 的代理程序。
     */
    public static void premain(String agentOps, Instrumentation inst) {
        log.info("====premain 方法执行");
//        Class[] allLoadedClasses = inst.getAllLoadedClasses();
//        for (Class allLoadedClass : allLoadedClasses) {
//            log.info("{}", allLoadedClasses.getClass().getName());
//        }
//        log.info(agentOps);
        inst.addTransformer(new Transformer());
    }

    /**
     * 如果不存在 premain(String agentOps, Instrumentation inst)
     * 则会执行 premain(String agentOps)
     *
     * @param agentOps
     */
    public static void premain(String agentOps) {
        log.info("====premain方法执行2====");
        log.info(agentOps);
    }

}