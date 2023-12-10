package com.bitc.spring_proj.mapper;

import com.bitc.spring_proj.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO user) throws Exception;

    int cntUser(UserDTO user) throws Exception;

    int isUserInfo(String uId, String uPw) throws Exception;

    UserDTO getUserInfo(String uId) throws Exception;
}
