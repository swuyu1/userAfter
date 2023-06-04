package com.example.swuyu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Found {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String category;
    private String initiator;
    private String title;
    private String imgs;
    private String type;
    private String introduce;
    private String state;
    private String phone;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtModified;

    public Found(String initiator, String title, String imgs, String type, String introduce, String state, String phone) {
        this.initiator = initiator;
        this.title = title;
        this.imgs = imgs;
        this.type = type;
        this.introduce = introduce;
        this.state = state;
        this.phone = phone;
    }
}
