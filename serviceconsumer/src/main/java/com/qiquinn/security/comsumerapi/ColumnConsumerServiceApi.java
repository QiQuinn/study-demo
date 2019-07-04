package com.qiquinn.security.comsumerapi;


import com.qiquinn.security.entity.ColumnInfo;
import com.qiquinn.security.service.ColumnInfoService;

import java.util.List;

public class ColumnConsumerServiceApi
{
    private ColumnInfoService columnService;

    public List<ColumnInfo> getColumn()
    {
        return columnService.select();
    }
}
