package com.zhangzemin;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jTest.class);
    /**
     * 快速入门
     */
    @Test
    public void testQuick(){
        //日志输出
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        //使用占位符输出日志信息
        String name = "张三";
        int age = 18;
        LOGGER.info("用户：{},{}",name,age);

        //讲系统的异常输出
        try {
            int i = 1/0;
        }catch (Exception e){
//            e.printStackTrace();
            LOGGER.error("出现异常",e);
        }
    }
}
