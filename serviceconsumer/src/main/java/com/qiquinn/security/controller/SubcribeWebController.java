package com.qiquinn.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/subcribe")
public class SubcribeWebController
{
    public void subcribe(HttpServletRequest request, HttpServletResponse response,String topic)
    {

    }

    public void pushService(String topic,String content)
    {

    }
}
