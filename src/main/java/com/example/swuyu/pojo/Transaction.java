package com.example.swuyu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String category;
    private String initiator;
    private String title;
    private String imgs;
    private String type;
    private String introduce;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;
    private String state;
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")

    private Date gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")

    private Date gmtModified;
}
