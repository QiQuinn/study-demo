package com.qiquinn.security.service;

import com.qiquinn.security.entity.ArticleInfo;
import com.qiquinn.security.service.basic.BasicService;

import java.util.List;

public interface ArticleService<T> extends BasicService<ArticleInfo>
{
    @Override
    void insert(ArticleInfo articleInfo);

    @Override
    void delete(ArticleInfo articleInfo);

    @Override
    void update(ArticleInfo articleInfo);

    @Override
    List<ArticleInfo> select();

    @Override
    ArticleInfo findById(Integer id);
}
