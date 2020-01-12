package com.example.mvc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mvc.model.ArticleInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
public interface IArticleInfoService extends IService<ArticleInfo> {

    Page<ArticleInfo> articleList(String key, int pageNo, int pageSize);

    boolean saveArticle(ArticleInfo articleInfo);

    boolean updateArticle(ArticleInfo articleInfo);
}
