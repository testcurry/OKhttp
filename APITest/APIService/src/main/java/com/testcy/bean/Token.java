package com.testcy.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Token {

    private Integer id;
    private String username;
    private String token;

}
