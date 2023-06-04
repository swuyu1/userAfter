package com.example.swuyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.swuyu.mapper.TransactionMapper;
import com.example.swuyu.pojo.Transaction;
import com.example.swuyu.service.TransactionServlet;
import org.springframework.stereotype.Service;


@Service
public class TransactionServletImpl extends ServiceImpl<TransactionMapper, Transaction> implements TransactionServlet {

}