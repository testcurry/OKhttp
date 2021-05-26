package com.testcy.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ResultMsg {

    private Integer code;
    private String msg;

    List<Article> articles;
}
