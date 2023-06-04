package com.example.swuyu.service;


import org.springframework.stereotype.Service;


@Service
public interface UtilServlet {

    /**
     * 获取access_token
     * @return access_token
     */
    public String getAccessToken();

}
