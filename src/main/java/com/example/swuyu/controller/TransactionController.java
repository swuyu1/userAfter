package com.example.swuyu.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.swuyu.pojo.Transaction;
import com.example.swuyu.service.TransactionServlet;
import com.example.swuyu.util.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(("/transaction"))
@CrossOrigin
public class TransactionController {
    @Autowired
    private TransactionServlet transactionServlet;

    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 添加交易
     *
     * @param transaction 数据
     * @return 是否成功
     */
    @PostMapping()
    public Result<Object> addTransaction(@RequestBody Transaction transaction) {
        boolean i = transactionServlet.save(transaction);
        if (i) {
            return Result.buildResult(Result.Code.OK);
        }
        return Result.buildResult(Result.Code.INTERNAL_SERVER_ERROR);
    }

    /**
     * 分页获取数据
     *
     * @param queryObj 参数
     *                 pageNum 当前页面
     *                 pageSize 一页的数量
     *                 query 模糊搜索条件
     * @return 分页后的数据
     */
    @GetMapping()
    public Result<Page<Transaction>> getAllTransaction(@RequestParam Object queryObj) {
//        将数据转为json
        JSONObject jsonObject = JSONObject.parseObject((String) queryObj);
//        获取json中数据
        String openId = (String) jsonObject.get("openId");
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        String query = (String) jsonObject.get("query");
        String state = (String) jsonObject.get("state");
//        创建分页条件
        Page<Transaction> page = new Page<>(pageNum, pageSize);
//        设置排序条件
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem = new OrderItem("look+0", false);
        orderItems.add(orderItem);
        page.setOrders(orderItems);
//        设置查询条件
        QueryWrapper<Transaction> wrapper = new QueryWrapper<>();
//        设置查询内容
        wrapper.select("id", "title", "imgs", "type")
//                设置查询条件(state==进行中)
                .like("state", state)
//                设置模糊搜索关键字
                .like("title", query)
                .like("initiator", openId);
//        获取数据
        Page<Transaction> transactionPage = transactionServlet.page(page, wrapper);
        logger.info("查找条件为" + jsonObject + "的交易");
        return Result.buildResult(Result.Code.OK, "查找条件为" + jsonObject + "的交易", transactionPage);
    }
    @GetMapping("/{id}")
    public Result<Transaction> getById(@PathVariable String id){
        Transaction transaction = transactionServlet.getById(id);
        logger.info("成功查询id为"+id+"的交易");
        UpdateWrapper wrapper = new UpdateWrapper();
        Long id1= Long.valueOf(id);
        wrapper.eq("id",id1);
        wrapper.setSql("look = look + 1");
        transactionServlet.update(wrapper);
        return Result.buildResult(Result.Code.OK,"成功查询id为"+id+"的交易",transaction);
    }
//    /**
//     * 修改交易订单状态（已取消/已完成）
//     *
//     * @param id    订单id
//     * @param state 状态
//     * @return 是否成功
//     */
//    @PutMapping("/setTransactionByState")
//    boolean setTransactionByState(@Param("id") Integer id, @Param("state") String state) {
//        return transactionServlet.setTransactionByState(id, state);
//    }
//
}
