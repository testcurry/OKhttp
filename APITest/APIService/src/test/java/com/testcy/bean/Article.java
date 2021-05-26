package com.testcy.bean;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Article {
    /*
    *  "id":299076,
	  "category":"article",
	  "subject":"荔枝新闻",
	  "summary":"江苏广电旗下资讯类手机应用 "荔枝新闻" 于近期推出全新升级换代的3.0版",
	  "changed":"2020-11-20 16:01:46"
    * */

    private Integer id;
    private String category;
    private String subject;
    private String summary;
    private Date changed;
}
