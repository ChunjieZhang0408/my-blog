package com.example.mvc.service;

import com.example.mvc.model.ArticleType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
public interface IArticleTypeService extends IService<ArticleType> {

    List<ArticleType> listByStatus();

}
