package com.example.mvc.service.impl;

import com.example.mvc.model.ArticleComment;
import com.example.mvc.mapper.ArticleCommentDao;
import com.example.mvc.service.IArticleCommentService;
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
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentDao, ArticleComment> implements IArticleCommentService {

}
