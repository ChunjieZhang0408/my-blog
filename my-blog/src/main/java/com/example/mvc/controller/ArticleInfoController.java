package com.example.mvc.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.constant.Constants;
import com.example.mvc.model.ArticleInfo;
import com.example.mvc.service.IArticleInfoService;
import com.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/article-info")
public class ArticleInfoController {

    @Autowired
    private IArticleInfoService articleInfoService;

    public Result list(String keyword,int pageNo,int pageSize){
        Page<ArticleInfo> articleInfoPage = articleInfoService.articleList(keyword, pageNo, pageSize);
        return Result.ok(Constants.MSG,"查詢成功").set(Constants.RESULT,articleInfoPage);
    }


    @RequestMapping(value = "/article",method = RequestMethod.POST)
    public Result save(@RequestBody ArticleInfo articleInfo){
        boolean success = articleInfoService.saveArticle(articleInfo);
        if (success){
            return Result.ok(Constants.MSG,"保存成功");
        }
        return Result.fail(Constants.MSG,"保存失败");
    }

    @PutMapping(value = "/article/{id}")
    public Result update(@PathVariable("id")String id,@RequestBody ArticleInfo articleInfo){
        boolean updateArticle = articleInfoService.updateArticle(articleInfo);
        return updateArticle?Result.ok(Constants.MSG,"修改成功"):Result.fail(Constants.MSG,"修改失敗");
    }
}

