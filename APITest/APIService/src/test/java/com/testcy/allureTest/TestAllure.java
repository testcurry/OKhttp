package com.testcy.allureTest;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;

@SpringBootTest
public class TestAllure {

    @Test
    @DisplayName("测试allure")
    public void test01(){
        System.out.println("这是我的allure测试。。。");
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("测试allure")
    public void test02() throws IOException {
        System.out.println("这是我的allure测试。。。");
        Assertions.assertTrue(true);
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.31.38:8080/token/username?userName=testops")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();

    }

}
