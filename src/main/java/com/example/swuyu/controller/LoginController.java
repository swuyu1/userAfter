package com.example.swuyu.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.swuyu.util.Result;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginController {

    @GetMapping("/login")
    //获取openid
    public static Result<String> getWxUserOpenid(String code) {
        //拼接url
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=");//appid设置
        url.append("wxddf108768335b997");
        url.append("&secret=");//secret设置
        url.append("9521d2c51f9359cb464761ff36885ea1");
        url.append("&js_code=");//code设置
        url.append(code);
        url.append("&grant_type=authorization_code");
        JSONObject res = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
//            获取返回数据
            String content = EntityUtils.toString(result);
//            将返回数据转为json
            res = JSONObject.parseObject(content);
//            获取openid
            String openid = (String) res.get("openid");
//            设置为token
            return Result.buildResult(Result.Code.OK,"获取成功",openid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.buildResult(Result.Code.INTERNAL_SERVER_ERROR,"获取失败");
    }

}
