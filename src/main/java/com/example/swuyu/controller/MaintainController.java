package com.example.swuyu.controller;

import com.example.swuyu.pojo.Maintain;
import com.example.swuyu.service.MaintainServlet;
import com.example.swuyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/maintain")
public class MaintainController {
    @Autowired
    private MaintainServlet maintainServlet;

    @PostMapping("")
    public Result<Object> addMaintain(@RequestBody Maintain maintain) {
        boolean i = maintainServlet.save(maintain);
        if (i) {
            return Result.buildResult(Result.Code.OK);
        }
        return Result.buildResult(Result.Code.INTERNAL_SERVER_ERROR);
    }


}
