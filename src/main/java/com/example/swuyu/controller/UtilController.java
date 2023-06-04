package com.example.swuyu.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.swuyu.lang.CheckEnum;
import com.example.swuyu.service.*;
import com.example.swuyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UtilController {
    @Autowired
    UtilServlet utilServlet;
    @Autowired
    MaintainServlet maintainServlet;
    @Autowired
    FoundServlet foundServlet;
    @Autowired
    TransactionServlet    transactionServlet;
    @Autowired
    RecycleServlet recycleServlet;

    /**
     * 文本内容验证
     * 官方文档 https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/sec-center/sec-check/msgSecCheck.html
     *
     * @param openid  openID
     * @param content 文本内容
     * @return 检验结果
     */
    @GetMapping("/msgSecCheck")
    public Result msgSecCheck(String openid, String content) {
//        获取accessToken
        String accessToken = utilServlet.getAccessToken();
//        设置请求头数据
        Map<String, Object> paramMap = new HashMap<String, Object>(1);
        paramMap.put("content", content);
        paramMap.put("openid", openid);
        paramMap.put("version", 2);
        paramMap.put("scene", 2);
//        发送请求
        Object result = HttpUtil.post("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + accessToken, JSONUtil.toJsonPrettyStr(paramMap));
//        接收结果并处理数据
        JSONObject jsonObject = JSONUtil.parseObj(result);
        Integer errcode = (Integer) jsonObject.get("errcode");
        String errmsg = (String) jsonObject.get("errmsg");
        if (errcode != 0) {
//        请求失败
            return Result.buildResult(Result.Code.BAD_REQUEST, errmsg);
        }
//        获取数据内容并获取命中标签枚举值
        JSONArray array = JSON.parseArray(jsonObject.get("detail").toString());
        JSONObject o = JSONUtil.parseObj(array.get(0));
        if (o == null) {
            o = JSONUtil.parseObj(array.get(1));
        }
        Integer label = (Integer) o.get("label");
//        查找命中标签枚举值权举类
        CheckEnum checkEnum = CheckEnum.findEnumByKey(label);
        if (checkEnum.getKey() != 100) {
//            审核不通过
            return Result.buildResult(Result.Code.BAD_REQUEST, checkEnum.getMessage());
        }
        return Result.buildResult(Result.Code.OK, "审核通过");
    }

    @GetMapping("/order")
    public Result getOrder(@RequestParam("openId") String openId) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("initiator", openId);
        List list = maintainServlet.list(wrapper);
        List list1 = foundServlet.list(wrapper);
        List list2 = transactionServlet.list(wrapper);
        List list3 = recycleServlet.list(wrapper);
        List l = new ArrayList<>();
        l.add(list);
        l.add(list1);
        l.add(list2);
        l.add(list3);
        return Result.buildResult(Result.Code.OK,l);
    }
}
