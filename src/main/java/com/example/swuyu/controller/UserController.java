package com.example.swuyu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.swuyu.pojo.User;
import com.example.swuyu.service.UserServlet;
import com.example.swuyu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    UserServlet userServlet;

    @GetMapping("/{openid}")
    public Result getUser(@PathVariable String openid) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("openid", openid);
        User user = userServlet.getOne(userQueryWrapper);
//        user不存在
        if (user == null) {
            User user1 = new User();
            user1.setOpenid(openid);
            boolean b = userServlet.save(user1);
            if (b) {
                log.info(openid + "不存在，创建成功");
            } else {
                log.info(openid + "不存在，创建失败");
            }
        }
        user = userServlet.getOne(userQueryWrapper);
        return Result.buildResult(Result.Code.OK, "获取用户信息", user);
    }

    @PostMapping("")
    public Result add(@RequestBody User user) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("openid", user.getOpenid());
        boolean update = userServlet.update(user, wrapper);
        if (update) {
            return Result.buildResult(Result.Code.OK);
        }
        return Result.buildResult(Result.Code.INTERNAL_SERVER_ERROR);
    }
}
