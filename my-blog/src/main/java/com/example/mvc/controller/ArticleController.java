package com.example.mvc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.config.annotation.RecordOperationLog;
import com.example.constant.Constants;
import com.example.mvc.model.ArticleInfo;
import com.example.mvc.model.ArticleType;
import com.example.mvc.model.MemberInfo;
import com.example.mvc.service.IArticleInfoService;
import com.example.mvc.service.IArticleTypeService;
import com.example.mvc.service.IMemberInfoService;
import com.example.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Desc ArticleController
 * @Author ZhangChunjie
 * @Date 2020/1/18 0:42
 * @Version 1.0
 */
@Controller
public class ArticleController {

    @Autowired
    private IArticleInfoService articleInfoService;

    @Autowired
    private IArticleTypeService articleTypeService;

    @Autowired
    private IMemberInfoService memberInfoService;

    @RequestMapping(value = {"/", "/article"})
    @RecordOperationLog
    public String index(@RequestParam(name = "keyword", required = false) String keyword,
                        @RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo,
                        @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
        Page<ArticleInfo> articleInfoPage = articleInfoService.articleList(keyword, pageNo, pageSize);
        Map<String, List<ArticleInfo>> articleInfoHashMap = articleInfoService.sortByDateStr(articleInfoPage.getRecords());
        model.addAttribute("articleInfoHashMap", articleInfoHashMap);
        return "index";
    }

    @GetMapping("/addArticlePage")
    public String addArticlePage(Model model) {
        List<ArticleType> articleTypes = articleTypeService.listByStatus();
        model.addAttribute("articleTypes", articleTypes);
        return "addArticle";
    }

    @PostMapping("/addArticle")
    @RecordOperationLog
    @ResponseBody
    public Result addArticle(@RequestBody ArticleInfo articleInfo) {
        articleInfo.setUserId("1218152760495570946");
        articleInfoService.saveArticle(articleInfo);
        return Result.ok(Constants.CODE, 0);
    }

    @GetMapping(value = "/article/{id}")
    public String details(@PathVariable(name = "id") String id, Model model) {
        ArticleInfo articleDetails = articleInfoService.findArticleById(id);
        model.addAttribute("articleDetails", articleDetails);
        String userId = articleDetails.getUserId();
        if (StringUtils.isNotBlank(userId)) {
            MemberInfo memberInfo = memberInfoService.findMemberById(userId);
            model.addAttribute("authorInfo", memberInfo);
        }
        return "details";
    }

}
