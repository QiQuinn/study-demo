package com.qiquinn.security.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qiquinn.security.dao.ArticleInfoDao;
import com.qiquinn.security.entity.ArticleInfo;
import com.qiquinn.security.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = ArticleService.class)
public class ArticleServiceImpl implements ArticleService<ArticleInfo>
{
    @Autowired
    private ArticleInfoDao articleInfoDao;

    @Override
    public void insert(ArticleInfo articleInfo) {
        articleInfoDao.insertData(articleInfo);
    }

    @Override
    public void delete(ArticleInfo articleInfo) {
        articleInfoDao.deleteData(articleInfo);
    }

    @Override
    public void update(ArticleInfo articleInfo) {
        articleInfoDao.updataData(articleInfo);
    }

    @Override
    public List<ArticleInfo> select() {
        return articleInfoDao.findAllData();
    }

    @Override
    public ArticleInfo findById(Integer id) {
        return articleInfoDao.findById(id);
    }
}
