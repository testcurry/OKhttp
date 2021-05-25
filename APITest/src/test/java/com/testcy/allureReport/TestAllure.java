package com.testcy.allureReport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestAllure {

    @Test
    @DisplayName("测试allure")
    public void test01(){
        System.out.println("这是我的allure测试。。。");
        Assertions.assertTrue(true);
    }

}
