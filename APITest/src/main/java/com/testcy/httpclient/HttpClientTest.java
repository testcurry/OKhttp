package com.testcy.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {

    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    @BeforeEach
    public void init() {

        System.out.println("....");
        HttpHost proxy = new HttpHost("127.0.0.1", 11111);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
         this.httpClient = HttpClients.custom()
                .setRoutePlanner(routePlanner)
                .build();
    }

    @Test
    public void test() throws IOException {

        String url = "http://localhost:8080/token";
        HttpGet get = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        System.out.println("正文类型：" + entity.getContentType());
        System.out.println(result);
        httpClient.close();
        response.close();
    }

    @Test
    public void testWithParam() throws URISyntaxException, IOException {
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("userName", "testops"));
        URI uri = new URIBuilder()
                .addParameters(parameters)
                .setScheme("http")
                .setHost("localhost")
                .setPort(8080)
                .setPath("/token/username")
                .build();
        HttpGet get = new HttpGet(uri);
        CloseableHttpResponse response = httpClient.execute(get);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }


}
