package com.example.swuyu.util;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSONArray;

import java.util.*;


public class Util {

    /**
     * json转map
     *
     * @param json 需要转的json
     * @return map
     */
    public static Map<String, Object> parseJSONMap(JSONObject json) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 最外层解析
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            // 如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                @SuppressWarnings("unchecked")
                Iterator<Object> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = (JSONObject) it.next();
                    list.add(parseJSONMap(json2));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    /**
     * 正则判断邮箱格式
     *
     * @param email 邮箱
     * @return 是否正确
     */
    private static Boolean checkEmail(String email) {
        return email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$");
    }

    /**
     * 正则判断手机号格式
     *
     * @param phone 手机号
     * @return 是否正确
     */
    private static Boolean checkPhone(String phone) {
        return phone.matches("^((13[0-9])|(14(0|[5-7]|9))|(15([0-3]|[5-9]))|(16(2|[5-7]))|(17[0-8])|(18[0-9])|(19([0-3]|[5-9])))\\\\d{8}$");
    }

    /**
     * 生成指定位数的随机数
     *
     * @param bitNum 位数
     * @return 随机数
     */
    public static String getRandomNum(int bitNum) {
        int[] s = new int[bitNum];
        for (int i = 0; i < s.length; i++) {
            s[i] = (int) (Math.random() * 10);
        }
        StringBuilder randomNum = new StringBuilder();
        for (int j : s) {
            randomNum.append(String.valueOf(j));
        }
        return String.valueOf(randomNum);
    }


}
