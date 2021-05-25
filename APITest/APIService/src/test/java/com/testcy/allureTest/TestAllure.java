package com.testcy.allureTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class TestAllure {

    @Test
    @DisplayName("测试allure")
    public void test01(){
        System.out.println("这是我的allure测试。。。");
        Assertions.assertTrue(true);
    }

}
