package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.ArticleInfo;
import com.qiquinn.security.service.ArticleService;
import com.qiquinn.security.utils.Exceptions.CustomerExpection;
import com.qiquinn.security.utils.Exceptions.ErrorEnum;
import com.qiquinn.security.utils.ResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        if(id==2)
        {
            throw new CustomerExpection(ErrorEnum.ERROR_USER_NULL.getCode()
                    ,ErrorEnum.ERROR_USER_NULL.getMsg()
                    ,this.getClass().toString());
        }
        ArticleInfo articleInfo = articleService.findById(id);
        return ResultUtils.seccuss(articleInfo);
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insertArticle(@RequestBody @Valid ArticleInfo article)
    {
        System.out.println(article.toString());
        articleService.insert(article);
    }

}
