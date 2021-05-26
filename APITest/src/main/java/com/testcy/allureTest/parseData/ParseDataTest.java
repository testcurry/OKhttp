package com.testcy.allureTest.parseData;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.testcy.allureTest.bean.Article;
import com.testcy.allureTest.bean.ResultMsg;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ParseDataTest {

    public String readFile(String fileUri) throws IOException {

        String data = "";
        FileReader fileReader = new FileReader(fileUri);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            data += str;
        }
        bufferedReader.close();
        return data;

    }

    @Test
    public void testJackson() throws IOException {
        String jsonStr = readFile("src/main/resources/demoJsonG.json");
        ObjectMapper objectMapper = new ObjectMapper();
        ResultMsg resultMsg = objectMapper.readValue(jsonStr, ResultMsg.class);
        System.out.println(resultMsg);
    }

    @Test
    public void testFastjson() throws IOException {
        String jsonStr = readFile("src/main/resources/demoJsonG.json");
        ResultMsg resultMsg = JSON.parseObject(jsonStr, ResultMsg.class);
        System.out.println(resultMsg);


    }

    @Test
    public void testGson() throws IOException {
        String jsonStr = readFile("src/main/resources/demoJsonG.json");
        Gson gson = new Gson();
        ResultMsg resultMsg = gson.fromJson(jsonStr, ResultMsg.class);
        System.out.println(resultMsg);


    }

    @Test
    public void parseJsonTest() throws IOException, ParseException {

        String file = readFile("src/main/resources/demoJson.json");
//        System.out.println(file);
        ResultMsg resultMsg = new ResultMsg();
        JSONObject jsonObject = new JSONObject(file);
        System.out.println(jsonObject);
        Integer code = (Integer) jsonObject.get("code");
        String msg = (String) jsonObject.get("msg");
        resultMsg.setCode(code);
        resultMsg.setMsg(msg);
        List<Article> articles = new ArrayList<>();
        JSONArray news = jsonObject.getJSONArray("news");
        System.out.println(news.toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (int i=0;i<news.length();i++){
            JSONObject jsonObject1 = news.getJSONObject(i);
            Article article = new Article();
            article.setId(Integer.parseInt(jsonObject1.get("id").toString()));
            article.setCategory(jsonObject1.get("category").toString());
            article.setSummary(jsonObject1.get("summary").toString());
            article.setSubject(jsonObject1.get("subject").toString());
            article.setChanged(dateFormat.parse(jsonObject1.get("changed").toString()));
            System.out.println(article);
            articles.add(article);
        }

       resultMsg.setArticles(articles);
        System.out.println(resultMsg);

    }
}
