package com.example.swuyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.swuyu.mapper.AdminMapper;
import com.example.swuyu.pojo.Admin;
import com.example.swuyu.service.AdminServlet;
import org.springframework.stereotype.Service;

@Service
public class AdminServletImpl extends ServiceImpl<AdminMapper, Admin> implements AdminServlet {

}