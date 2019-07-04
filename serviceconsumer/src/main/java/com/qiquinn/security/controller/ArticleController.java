package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.ArticleInfo;
import com.qiquinn.security.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController
{
    @Reference
    private ArticleService articleService;

    @RequestMapping(value = "/getarticle/{id}",method = RequestMethod.GET)
    ArticleInfo getAriticleById(Integer id)
    {
        System.out.println(id);
        return articleService.findById(id);
    }

}
