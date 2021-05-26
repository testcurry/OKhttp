package com.testcy.okhttpClient;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookieManager {

    Map<String, List<Cookie>> cookieMap = new HashMap<>();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                    cookieMap.put(httpUrl.toString(), list);
                    System.out.println("save from url:" + httpUrl.toString());
                    for (Cookie cookie : list) {
                        System.out.println("获取的cookie信息：" + cookie);
                    }
                }

                @NotNull
                @Override
                public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                    if (cookieMap.get(httpUrl.toString()) == null) {
                        return new ArrayList<Cookie>();
                    }
                    return cookieMap.get(httpUrl.toString());
                }
            })
            .build();

    public String getCookieFromRequest(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    public void addCookieInRequest(String url, Map<String, String> map) throws IOException {
        List<Cookie> cookieList = okHttpClient.cookieJar().loadForRequest(HttpUrl.get(url));
        ArrayList<Cookie> newCookieList = new ArrayList<>(cookieList);
        for (String key:map.keySet()){
            Cookie cookie = new Cookie.Builder()
                    .name(key)
                    .value(map.get(key))
                    .domain("baidu.com")
                    .path("/")
                    .build();
            newCookieList.add(cookie);
        }
        okHttpClient.cookieJar().saveFromResponse(HttpUrl.get(url), newCookieList);
    }

    public void requestWithCookies(String url) throws IOException {
        List<Cookie> cookieList = okHttpClient.cookieJar().loadForRequest(HttpUrl.get(url));
        okHttpClient.cookieJar().saveFromResponse(HttpUrl.get(url), cookieList);
    }

}
