package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.ArticleInfo;
import com.qiquinn.security.service.ArticleService;
import com.qiquinn.security.utils.Exceptions.CustomerExpection;
import com.qiquinn.security.utils.stringenum.MessageEnum;
import com.qiquinn.security.utils.ResultSerlizerUtils;
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
    public Map<String,Object> getAriticleById(@PathVariable(value = "id") Integer id)
    {
        ArticleInfo articleInfo = articleService.findById(id);
        if(articleInfo==null)
        {
            throw new CustomerExpection(MessageEnum.ERROR_USER_NULL.getCode()
                    , MessageEnum.ERROR_USER_NULL.getMsg()
                    ,"getAriticleById",this);
        }
        return ResultSerlizerUtils.seccuss(articleInfo);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insertArticle(@RequestBody @Valid ArticleInfo article)
    {
        System.out.println(article.toString());
        articleService.insert(article);
    }

}
