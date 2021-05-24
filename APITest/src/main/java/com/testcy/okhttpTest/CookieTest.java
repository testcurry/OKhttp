package com.testcy.okhttpTest;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

public class CookieTest {

    @BeforeEach
    public void init(){
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "11111");
        System.setProperty("https.proxyPort", "11111");
    }

    @Test
    public void testCookie() throws IOException {
        CookieManager cookieManager = new CookieManager();
        String cookie = cookieManager.getCookieFromRequest("http://www.baidu.com");
        System.out.println(cookie);
  /*      OkHttpClient okHttpClient = new OkHttpClient();
        Request requestWithCookie = new Request.Builder().addHeader("Cookie", cookie).url("http://www.baidu.com/").build();
        Response response = okHttpClient.newCall(requestWithCookie).execute();
        System.out.println(response.body().string());*/
    }

    @Test
    public void testAddCookie() throws IOException {
        CookieManager cookieManager = new CookieManager();
        HashMap<String, String> map = new HashMap<>();
        map.put("login", "true");
        map.put("testops", "test111");
        map.put("Curry", "YYDS");
        String url="http://www.baidu.com";
        cookieManager.getCookieFromRequest(url);
        cookieManager.addCookieInRequest(url, map);
        Request request = new Request.Builder().url(url).build();
        Response response = cookieManager.okHttpClient.newCall(request).execute();
    }

    @Test
    public void testRequestWithCookies() throws IOException {
        CookieManager cookieManager = new CookieManager();
        String url="http://www.baidu.com";
        cookieManager.getCookieFromRequest(url);
        cookieManager.requestWithCookies(url);
        Request request = new Request.Builder().url(url).build();
        Response response = cookieManager.okHttpClient.newCall(request).execute();
    }


}
