package com.testcy.okhttpTest;

import com.testcy.utils.JsonUtils;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.*;

public class OkHttpTest {

    private static final MediaType JSON_MEDIA_TYPE=MediaType.get("application/json;charset=utf-8");
    private OkHttpClient client= new OkHttpClient();

    //get请求
    @Test
    public void testOkhttp() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/token")
                .get()
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    //异步请求
    @Test
    public void testOkhttpAsync() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/token")
                .get()
                .build();
       client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getCause());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }


    //post请求 json
    @Test
    public void testPostJson() throws IOException {

        String url="http://localhost:8080/token";
        String jsonStr = JsonUtils.readFile("src/main/resources/demoJsonG.json");
        RequestBody requestBody = RequestBody.create(jsonStr, JSON_MEDIA_TYPE);
        Request requestPost = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = client.newCall(requestPost).execute();
        System.out.println(response.body().string());

    }

    //post请求 x-form-www-urlencoded
    @Test
    public void testPostForm() throws IOException {
        String url="http://localhost:8080/token/form";
        FormBody formBody = new FormBody.Builder()
                .add("userName", "test01")
                .add("token", "testcy1234")
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }

    //文件上传
    @Test
    public void testUpload() throws IOException {
        String url="http://localhost:8080/file/upload";
        File file = new File(".//upload//devops.jpg");
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",
                        file.getName(),
                        RequestBody.create(file,MediaType.parse("file/*")))
                .addFormDataPart("userName", "testcy")
                .addFormDataPart("token", "test235234")
                .build();
       /* FormBody formBody = new FormBody.Builder()
                .add("userName", "testcy")
                .add("token", "test235234")
                .build();*/
        Request request = new Request.Builder()
                .post(multipartBody)
//                .post(formBody)
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());


    }

    //文件下载
    @Test
    public void testDownload() throws IOException {
        String filePath = ".//download";
        String url = "http://localhost:8080/file/download";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        String disposition = response.header("Content-Disposition").toString();
        String fileName = disposition.split(";")[0];
        ResponseBody body = response.body();
        InputStream inputStream = body.byteStream();
        File dir = new File(filePath);
        if (!dir.exists()){
            dir.mkdir();
        }
        File file = new File(filePath + fileName);
        OutputStream outputStream = new FileOutputStream(file);
        int len;
        byte[] buffer = new byte[1024];
        while ((len=inputStream.read(buffer))!=-1){
            outputStream.write(buffer, 0, len);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();

    }



}
