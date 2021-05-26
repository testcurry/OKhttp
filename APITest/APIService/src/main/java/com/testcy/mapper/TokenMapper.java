package com.testcy.mapper;

import com.testcy.allureTest.bean.Token;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TokenMapper {

    @Select("select * from token")
    public List<Token> getToken();

    @Select("select * from token where username=#{username}")
    public Token queryByUserName(String userName);

    @Insert("insert into token(`userName`,`token`) values(#{userName},#{token})")
    public void insert(Token token);

    @Insert("insert into token(`userName`,`token`) values(#{userName},#{token})")
    public void insertByJson(Token token);

}
