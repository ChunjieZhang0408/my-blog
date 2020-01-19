package com.example.mvc.controller.upload;

import com.example.constant.Constants;
import com.example.util.HttpClientUtils;
import com.example.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Desc UploadController
 * @Author ZhangChunjie
 * @Date 2020/1/19 10:55
 * @Version 1.0
 */
@RestController
@Slf4j
public class UploadController {

    private String uploadUrl = "http://www.gdni.cn:8082/upload";

    private String filePathPrefix = "http://www.gdni.cn:8001/";

    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(@RequestParam(name = "file") MultipartFile file) throws IOException {
        HttpClientUtils.upload(uploadUrl, file, new HashMap<>());
        String filename = file.getOriginalFilename();
        List<String> data = new ArrayList<>(4);
        data.add(filePathPrefix + filename);
        return Result.create("errno", 0).set(Constants.MSG, "上传成功").set(Constants.DATA, data);

    }
}
