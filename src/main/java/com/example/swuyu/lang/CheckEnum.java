package com.example.swuyu.lang;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;


public enum CheckEnum {

    NORMAL(100,"正常"),
    ADVERTISEMENT(10001,"广告"),
    POLITICS(20001,"时政"),
    PORNOGRAPHY(20002,"色情"),
    ABUSE(20003,"辱骂"),
    DELINQUENCY(20006,"违法犯罪"),
    FRAUD(20008,"欺诈"),
    VULGAR(20012,"低俗"),
    COPYRIGHT(20013,"版权"),
    OTHER(21000,"其他");
    @EnumValue
    private Integer key;

    @JsonValue
    private String message;

    CheckEnum(Integer key, String message) {
        this.key = key;
        this.message = message;
    }

    public Integer getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 根据key查找
     * @param key 枚举key
     * @return 枚举对象
     */
    public static CheckEnum findEnumByKey(Integer key) {
        for (CheckEnum statusEnum : CheckEnum.values()) {
            if (statusEnum.getKey().equals(key)) {
                //如果需要直接返回name则更改返回类型为String,return statusEnum.name;
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("key is invalid");
    }

    /**
     * 根据message查找
     * @param message 枚举message
     * @return 枚举对象
     */
    public static CheckEnum findEnumByMessage(String message) {
        for (CheckEnum statusEnum : CheckEnum.values()) {
            if (statusEnum.getMessage().equals(message)) {
                //如果需要直接返回code则更改返回类型为String,return statusEnum.code;
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("name is invalid");
    }

}
