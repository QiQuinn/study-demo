package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.ColumnInfo;
import com.qiquinn.security.service.ColumnInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/column")
public class ColumnController
{
    @Reference
    private ColumnInfoService columnInfoService;

    @RequestMapping(value = "/getcolumn",method = RequestMethod.GET)
    public List<ColumnInfo> getColumnInfo()
    {
        return columnInfoService.select();
    }
}
