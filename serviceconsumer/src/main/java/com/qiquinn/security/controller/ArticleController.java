package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.ArticleInfo;
import com.qiquinn.security.service.ArticleService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController
{
    @Reference
    private ArticleService articleService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getSAriticleById(@PathVariable(value = "id") Integer id)
    {
        System.out.println("id= "+id);
        ArticleInfo articleInfo = articleService.findById(id);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("ret","返回码");
        resultMap.put("msg","返回信息");
        resultMap.put("code","返回码编号");
        resultMap.put("message","");
        resultMap.put("data",articleInfo);
        return resultMap;
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insertArticle(@RequestBody @Valid ArticleInfo article)
    {
        System.out.println(article.toString());
        articleService.insert(article);
    }
}
