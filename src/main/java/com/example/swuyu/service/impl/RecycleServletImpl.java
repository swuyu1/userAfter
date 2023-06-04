package com.example.swuyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.swuyu.mapper.RecycleMapper;
import com.example.swuyu.pojo.Recycle;
import com.example.swuyu.service.RecycleServlet;
import org.springframework.stereotype.Service;


@Service
public class RecycleServletImpl extends ServiceImpl<RecycleMapper, Recycle> implements RecycleServlet {

}