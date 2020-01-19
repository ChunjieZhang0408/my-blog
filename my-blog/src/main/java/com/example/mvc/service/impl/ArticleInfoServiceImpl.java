package com.example.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mvc.mapper.ArticleInfoDao;
import com.example.mvc.model.ArticleInfo;
import com.example.mvc.service.IArticleInfoService;
import com.example.mvc.service.IMemberInfoService;
import com.example.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private IMemberInfoService memberInfoService;

    @Override
    public Page<ArticleInfo> articleList(String keyword, int pageNo, int pageSize) {
        Page<ArticleInfo> page = new Page<>();
        page.setPages(pageNo);
        page.setSize(pageSize);
        QueryWrapper<ArticleInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.like("title", keyword);
        }
        queryWrapper.eq("status", 1).orderByDesc("update_time", "create_time");
        Page<ArticleInfo> articleInfoPage = this.page(page, queryWrapper);
        return articleInfoPage;
    }

    @Override
    public boolean saveArticle(ArticleInfo articleInfo) {
        Date nowDate = new Date();
        articleInfo.setCreateTime(nowDate);
        articleInfo.setCreateTimeStr(DateUtils.formatDay(nowDate));
        articleInfo.setUpdateTime(nowDate);
        articleInfo.setUpdateTimeStr(DateUtils.formatDay(nowDate));
        articleInfo.setUserId("1218152760495570946");
        this.save(articleInfo);
        return false;
    }

    public boolean updateArticle(ArticleInfo articleInfo) {
        UpdateWrapper<ArticleInfo> updateWrapper = new UpdateWrapper<>();
        articleInfo.setUpdateTime(new Date());
        boolean update = this.update(articleInfo, updateWrapper);
        return update;
    }

    @Override
    public ArticleInfo findArticleById(String id) {
        QueryWrapper<ArticleInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("status", 1);
        ArticleInfo articleInfo = this.getOne(queryWrapper);
       return articleInfo;
    }

    @Override
    public Map<String, List<ArticleInfo>> sortByDateStr(List<ArticleInfo> articles) {
        Map<String, List<ArticleInfo>> articleInfoHashMap = new HashMap<>();
        for (ArticleInfo r : articles) {
            String dateStr = DateUtils.formatDay(r.getCreateTime());
            r.setCreateTimeStr(dateStr);
            List<ArticleInfo> articleInfo;
            if (articleInfoHashMap.containsKey(dateStr)) {
                articleInfoHashMap.get(dateStr).add(r);
            } else {
                articleInfo = new ArrayList<>();
                articleInfo.add(r);
                articleInfoHashMap.put(dateStr, articleInfo);
            }
        }
        return articleInfoHashMap;
    }
}
