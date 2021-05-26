package com.testcy.controller;

import com.alibaba.fastjson.JSON;
import com.testcy.bean.Token;
import com.testcy.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api(description = "token增删改查接口")
@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    //不带参数的get请求
    @ApiOperation(value = "获取token列表",httpMethod = "GET")
    @GetMapping("/token")
    public List<Token> token() {

        List<Token> allToken = tokenService.getAllToken();
        return allToken;

    }

    //带参数的get请求
    @ApiOperation(value = "根据用户名获取token",httpMethod = "GET")
    @GetMapping("/token/username")
    public Token token(@RequestParam("userName") String userName) {
        log.info("请求进来了。。");
        Token token = tokenService.queryByName(userName);
        return token;
    }

    //body为json的post请求
    @ApiOperation(value = "传入json格式的正文添加token",httpMethod = "POST")
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
    @ApiOperation(value = "传入键值对的正文添加token",httpMethod = "POST")
    public String addFormToken(@RequestParam("userName") String username,
                               @RequestParam("token") String token) {
        Token token1 = new Token();
        token1.setUserName(username);
        token1.setToken(token);
        tokenService.addTokenByJson(token1);
        return "新增成功！";
    }

}
