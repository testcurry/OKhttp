package com.testcy.controller;

import com.alibaba.fastjson.JSON;
import com.testcy.bean.Token;
import com.testcy.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    //不带参数的get请求
    @GetMapping("/token")
    public List<Token> token() {

        List<Token> allToken = tokenService.getAllToken();
        return allToken;

    }

    //带参数的get请求
    @GetMapping("/token/username")
    public Token token(@RequestParam("userName") String userName) {
        log.info("请求进来了。。");
        Token token = tokenService.queryByName(userName);
        return token;
    }

    //body为json的post请求
    @PostMapping("/token")
    public String addJsonToken(@RequestBody Token token) {
        if (Objects.isNull(token)) {
            return "参数不合法！";
        }
        if (token.getToken() == null || "".equals(token.getToken()) ||
                "".equals(token.getUserName()) || token.getUserName() == null) {
            return "参数不合法！";
        }
        tokenService.addToken(token);
        return "新增成功！";
    }

    //body为key-value的post请求
    @PostMapping("/token/form")
    public String addFormToken(@RequestParam("userName") String username,
                               @RequestParam("token") String token) {
        Token token1 = new Token();
        token1.setUserName(username);
        token1.setToken(token);
        tokenService.addTokenByJson(token1);
        return "新增成功！";
    }

}
