package com.bitc.spring_proj.service;


import com.bitc.spring_proj.dto.UserDTO;
import com.bitc.spring_proj.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(UserDTO user) throws Exception {
        userMapper.insertUser(user);
    }

    @Override
    public int cntUser(UserDTO user) throws Exception {
        return userMapper.cntUser(user);
    }

    @Override
    public int isUserInfo(String uId, String uPw) throws Exception {
        return userMapper.isUserInfo(uId,uPw);
    }

    @Override
    public UserDTO getUserInfo(String uId) throws Exception {
        return userMapper.getUserInfo(uId);
    }


}
