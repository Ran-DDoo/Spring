package com.bitc.spring_proj.service;


import com.bitc.spring_proj.dto.UserDTO;

public interface UserService {
    void insertUser(UserDTO user) throws Exception;

    int cntUser(UserDTO user) throws Exception;

    int isUserInfo(String uId, String uPw) throws Exception;

    UserDTO getUserInfo(String uId) throws Exception;
}
