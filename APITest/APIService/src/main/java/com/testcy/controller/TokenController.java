package com.testcy.controller;

import com.alibaba.fastjson.JSON;
import com.testcy.bean.Token;
import com.testcy.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/token")
    public List<Token> token() {

        List<Token> allToken = tokenService.getAllToken();
        return allToken;

    }

    @GetMapping("/token/username")
    public Token token(@RequestParam("userName") String userName) {
        log.info("请求进来了。。");
        Token token = tokenService.queryByName(userName);
        return token;
    }

}
