package com.example.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mvc.mapper.ArticleInfoDao;
import com.example.mvc.model.ArticleInfo;
import com.example.mvc.service.IArticleInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
@Service
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoDao, ArticleInfo> implements IArticleInfoService {

    @Override
    public Page<ArticleInfo> articleList(String key, int pageNo, int pageSize) {
        Page<ArticleInfo> page = new Page<>();
        page.setPages(pageNo);
        page.setSize(pageSize);
        QueryWrapper<ArticleInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", key)
                .eq("status", 1)
                .orderByDesc("update_time");
        Page<ArticleInfo> articleInfoPage = this.page(page, queryWrapper);
        return articleInfoPage;
    }

    @Override
    public boolean saveArticle(ArticleInfo articleInfo) {
        Date nowDate = new Date();
        articleInfo.setCreateTime(nowDate);
        articleInfo.setUpdateTime(nowDate);
        this.save(articleInfo);
        return false;
    }

    public boolean updateArticle(ArticleInfo articleInfo){
        UpdateWrapper<ArticleInfo> updateWrapper = new UpdateWrapper<>();
        articleInfo.setUpdateTime(new Date());
        boolean update = this.update(articleInfo, updateWrapper);
        return update;
    }
}
