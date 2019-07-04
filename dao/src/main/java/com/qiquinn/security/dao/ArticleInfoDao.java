package com.qiquinn.security.dao;

import com.qiquinn.security.dao.basic.BaseDao;
import com.qiquinn.security.entity.ArticleInfo;

import java.util.List;

public interface ArticleInfoDao extends BaseDao<ArticleInfo>
{
    @Override
    void updataData(ArticleInfo articleInfo);
    @Override
    List<ArticleInfo> findAllData();
    @Override
    void insertData(ArticleInfo articleInfo);
    @Override
    void deleteData(ArticleInfo articleInfo);

    @Override
    ArticleInfo findById(Integer id);

    List<ArticleInfo> findArticleByCulom(Integer columnId, Integer page, Integer amount);

}
