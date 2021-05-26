package com.testcy.allureTest.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    public static String readFile(String fileUri) throws IOException {

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
}
