package com.example.swuyu.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.swuyu.service.UtilServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UtilServletImpl implements UtilServlet {

    private static String ACCESS_TOKEN_KEY = "accessToken";


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<String, String> string;

    /**
     * 从redis中获取access_token
     * @return
     */
    @Override
    public String getAccessToken() {
//        查找accessToken是否存在
        if (redisTemplate.hasKey(ACCESS_TOKEN_KEY)) {
//            存在直接获取返回
            return string.get(ACCESS_TOKEN_KEY);
        } else {
//            不存在，重新获取
            String accessTokenVal = getAccessTokenVal();
            string.set(ACCESS_TOKEN_KEY, accessTokenVal);
            //设置过期时间
            redisTemplate.expire(ACCESS_TOKEN_KEY, 110, TimeUnit.MINUTES);
            return accessTokenVal;
        }

    }

    private static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取accessToken
     *
     * @return
     */
    private String getAccessTokenVal() {
        String url = GET_ACCESS_TOKEN_URL.replace("APPID", "wxddf108768335b997").replace("APPSECRET", "9521d2c51f9359cb464761ff36885ea1");
        String s = HttpUtil.get(url);
        JSONObject jsonObject = JSONUtil.parseObj(s);
        log.debug("获取accessToken" + jsonObject.toString());
        return (String) jsonObject.get("access_token");
    }

}
