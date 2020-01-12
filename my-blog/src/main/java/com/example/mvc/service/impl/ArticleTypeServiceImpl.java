package com.example.mvc.service.impl;

import com.example.mvc.model.ArticleType;
import com.example.mvc.mapper.ArticleTypeDao;
import com.example.mvc.service.IArticleTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeDao, ArticleType> implements IArticleTypeService {

}
