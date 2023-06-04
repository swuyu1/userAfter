package com.example.swuyu.controller;

import com.example.swuyu.pojo.Recycle;
import com.example.swuyu.service.RecycleServlet;
import com.example.swuyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(("/recycle"))
public class RecycleController {
    @Autowired
    private RecycleServlet recycleServlet;

    @PostMapping()
    public Result<Object> addMaintain(@RequestBody Recycle recycle) {
        boolean i = recycleServlet.save(recycle);
        if (i) {
            return Result.buildResult(Result.Code.OK);
        }
        return Result.buildResult(Result.Code.INTERNAL_SERVER_ERROR);
    }

}
