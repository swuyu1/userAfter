package com.example.swuyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.swuyu.mapper.MaintainMapper;
import com.example.swuyu.pojo.Maintain;
import com.example.swuyu.service.MaintainServlet;
import org.springframework.stereotype.Service;


@Service
public class MaintainServletImpl extends ServiceImpl<MaintainMapper, Maintain> implements MaintainServlet {

}