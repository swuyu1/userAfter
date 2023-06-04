package com.example.swuyu.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@CrossOrigin
@RestController
public class ImgUtil {

    @PostMapping("/imgs")
    public String addImgs(@RequestParam("file") MultipartFile file) {
        //        图片上传
//        格式化时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM-dd/");
        String directory = simpleDateFormat.format(new Date());
        String fileSavePath = "upload/imgs/";
//        文件保存路径（upload/imgs/yyyy/mm-dd/）
        File dir = new File(fileSavePath + directory);
//        创建文件夹
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //给文件重新设置一个名字
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        File targetFile = new File(dir.getAbsolutePath(), newFileName);
        try {
            file.transferTo(targetFile);
            String url = dir + "/" + newFileName;
            return url;
        } catch (IOException e) {
            return null;
        }
    }

}
