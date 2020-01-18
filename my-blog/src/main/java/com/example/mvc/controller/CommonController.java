package com.example.mvc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.config.annotation.RecordOperationLog;
import com.example.mvc.model.ArticleInfo;
import com.example.mvc.model.ArticleType;
import com.example.mvc.service.IArticleInfoService;
import com.example.mvc.service.IArticleTypeService;
import com.example.util.DateUtils;
import com.example.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Desc CommonController
 * @Author ZhangChunjie
 * @Date 2020/1/16 22:18
 * @Version 1.0
 */
@Controller
public class CommonController {



    @RequestMapping(value = "/captcha")
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        ImageUtils.captcha(request, response);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
