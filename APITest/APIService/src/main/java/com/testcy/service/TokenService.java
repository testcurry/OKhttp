package com.testcy.service;

import com.testcy.bean.Token;
import com.testcy.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    public List<Token> getAllToken(){

        List<Token> token = tokenMapper.getToken();
        return token;

    }

    public Token queryByName(String userName){
        Token token = tokenMapper.queryByUserName(userName);
        return token;
    }

    public void addToken(Token token){
        tokenMapper.insert(token);

    }

    public void addTokenByJson(Token token) {
         tokenMapper.insertByJson(token);
    }
}
