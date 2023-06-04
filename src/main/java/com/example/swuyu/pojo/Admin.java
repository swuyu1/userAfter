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
public class Admin {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String adminName;
    private String password;
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")

    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")

    private Date gmtModified;
}
