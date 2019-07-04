package com.qiquinn.security.test.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qiquinn.security.dao.SystemInfoDao;
import com.qiquinn.security.service.CulmService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = CulmService.class)
public class CulmServiceImpl implements CulmService
{
    @Autowired
    private SystemInfoDao systemInfoDao;
    @Override
    public String sayHello() {
        return "hello";
    }

    public void System()
    {
        systemInfoDao.select();
    }
}
