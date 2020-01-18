package com.example.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mvc.mapper.ArticleTypeDao;
import com.example.mvc.model.ArticleType;
import com.example.mvc.service.IArticleTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeDao, ArticleType> implements IArticleTypeService {

    @Override
    public List<ArticleType> listByStatus() {
        QueryWrapper<ArticleType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        return this.list(queryWrapper);
    }
}
