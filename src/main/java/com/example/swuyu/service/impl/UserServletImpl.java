package com.example.swuyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.swuyu.mapper.UserMapper;
import com.example.swuyu.pojo.User;
import com.example.swuyu.service.UserServlet;
import org.springframework.stereotype.Service;

@Service
public class UserServletImpl extends ServiceImpl<UserMapper, User> implements UserServlet {
}