package com.zhangzemin;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

public class JULTest {
    /**
     * 快速入门
     */
    @Test
    public void testQuick(){
        //1.获取日志记录器对象
        Logger logger = Logger.getLogger("com.zhangzemin.JULTest");
        //2.日志记录输出
        logger.info("hello jul");

        //通用方法进行日志记录
        logger.log(Level.INFO,"info msg");

        //通过占位符方式输出变量值
        String name = "itcast";
        Integer age = 18;
        logger.log(Level.INFO,"用户信息：{0}，{1}",new Object[]{name,age});
    }

    /**
     * 日志级别：jul默认的日志级别为info
     */
    @Test
    public void testLevel(){
        //1.获取日志记录器对象
        Logger logger = Logger.getLogger("com.zhangzemin.JULTest");
        //2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("logger");
    }

    /**
     * 自定义日志级别
     */
    @Test
    public void testLogConfig() throws IOException {
        //1.获取日志记录器对象
        Logger logger = Logger.getLogger("com.zhangzemin.JULTest");
        //关闭系统默认配置
        logger.setUseParentHandlers(false);

        //自定义配置日志级别
        /**
         * 控制台输出
         */
        //创建ConsoleHandler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        //创建简单格式转换对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        //进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        //配置日志具体级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        /**
         * 文件输出
         */
        FileHandler fileHandler = new FileHandler("jul.log");
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);


        //2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("logger");
    }

    /**
     * Logger对象父子关系
     */
    @Test
    public void testLogParent() throws Exception{
        Logger sonLogger = Logger.getLogger("com.zhangzemin");
        Logger parentLogger = Logger.getLogger("com");
        System.out.println(sonLogger.getParent() == parentLogger);
        //测试得知，所有日志记录器的顶级父元素是LogManager$RootLogger，name为""（空字符串）
        System.out.println("parentLogger parent:"+parentLogger.getParent()+",name:"+parentLogger.getParent().getName());



        /**
         * 测试子日志继承了父日志的日志级别
         */
        parentLogger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        consoleHandler.setFormatter(simpleFormatter);
        parentLogger.addHandler(consoleHandler);
        parentLogger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        sonLogger.severe("severe");
        sonLogger.warning("warning");
        sonLogger.info("info");
        sonLogger.config("config");
        sonLogger.fine("fine");
        sonLogger.finer("finer");
        sonLogger.finest("logger");
    }

    /**
     * 加载自定义配置文件
     */
    @Test
    public void testLogProperties() throws IOException {
        //读取配置文件，通过类加载器
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("logging.properties");
        //创建LogManger
        LogManager logManager = LogManager.getLogManager();
        //通过LogManger加载配置文件
        logManager.readConfiguration(is);

        //创建日志记录器
        Logger logger = Logger.getLogger("com.zhangzemin");
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("logger");
    }
}
