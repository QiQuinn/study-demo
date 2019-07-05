package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.ArticleInfo;
import com.qiquinn.security.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/article")
public class ArticleController
{
    @Reference
    private ArticleService articleService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    private ArticleInfo getAriticleById(Integer id)
    {
        System.out.println("getiid= "+id);
        return articleService.findById(2);
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    private void insertArticle(@RequestBody @Valid ArticleInfo article)
    {
        System.out.println(article.toString());
        articleService.insert(article);
    }
}
