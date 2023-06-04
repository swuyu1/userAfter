package com.example.swuyu.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.swuyu.pojo.Found;
import com.example.swuyu.service.FoundServlet;
import com.example.swuyu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(("/found"))
@CrossOrigin
@Slf4j
public class FoundController {
    @Autowired
    private FoundServlet foundServlet;


    /**
     * 添加失物招领
     *
     * @param param json格式的数据
     * @return 是否成功
     */
    @PostMapping()
    public Result<Object> addFound(@RequestBody Found found) {
        boolean i = foundServlet.save(found);
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
    public Result<Page<Found>> getAllFound(@RequestParam Object queryObj) {
//        将数据转为json
        JSONObject jsonObject = JSONObject.parseObject((String) queryObj);
//        获取json中数据
        String openId = (String) jsonObject.get("openId");
        Integer pageNum = (Integer) jsonObject.get("pageNum");
        Integer pageSize = (Integer) jsonObject.get("pageSize");
        String query = (String) jsonObject.get("query");
        String state = (String) jsonObject.get("state");
//        创建分页条件
        Page<Found> page = new Page<>(pageNum, pageSize);
//        设置排序条件
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem = new OrderItem("look+0", false);
        orderItems.add(orderItem);
        page.setOrders(orderItems);
//        设置查询条件
        QueryWrapper<Found> wrapper = new QueryWrapper<>();
//        设置查询内容
        wrapper.select("id", "title", "imgs", "type")
//                设置查询条件(state==进行中)
                .like("state", state)
//                设置模糊搜索关键字
                .like("title", query)
                .like("initiator", openId);
//        获取数据
        Page<Found> foundPage = foundServlet.page(page, wrapper);
        log.info("查找条件为" + jsonObject + "的失物招领");
        return Result.buildResult(Result.Code.OK, "查找条件为" + jsonObject + "的失物招领", foundPage);
    }

    @GetMapping("/{id}")
    public Result getId(@PathVariable String id) {
        Found found = foundServlet.getById(id);
        log.info("成功查询id为" + id + "的失物招领");
        UpdateWrapper wrapper = new UpdateWrapper();
        Long id1 = Long.valueOf(id);
        wrapper.eq("id", id1);
        wrapper.setSql("look = look + 1");
        foundServlet.update(wrapper);
        return Result.buildResult(Result.Code.OK, "成功查询id为" + id + "的失物招领", found);
    }
//    /**
//     * 修改失物招领订单状态（已取消/已完成）
//     *
//     * @param id    订单id
//     * @param state 状态
//     * @return 是否成功
//     */
//    @PutMapping("/setFoundByState")
//    boolean setFoundByState(@Param("id") Integer id, @Param("state") String state) {
//        return foundServlet.setFoundByState(id, state);
//    }
//
}
