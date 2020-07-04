package com.storage.cloud.mapper;

import com.storage.cloud.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE USERNAME = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USER (username,salt,password,firstname,lastname) VALUES (#{username},#{salt},#{password},#{firstname},#{lastname})")
    @Options(useGeneratedKeys = true,keyProperty = "userid")
    int addUser(User user);
}
