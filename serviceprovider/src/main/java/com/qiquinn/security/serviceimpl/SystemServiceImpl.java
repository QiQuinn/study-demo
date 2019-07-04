package com.qiquinn.security.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qiquinn.security.dao.SystemInfoDao;
import com.qiquinn.security.entity.SystemInfo;
import com.qiquinn.security.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = SystemService.class)
public class SystemServiceImpl  implements SystemService
{
    @Autowired
    private SystemInfoDao systemInfoDao;
    @Override
    public void insert(SystemInfo systeminfo) {
        systemInfoDao.insert(systeminfo);
    }

    @Override
    public void delete(SystemInfo systeminfo) {
        systemInfoDao.delete(systeminfo);
    }

    @Override
    public void update(SystemInfo systeminfo) {
        systemInfoDao.update(systeminfo);
    }

    @Override
    public List<SystemInfo> select() {
        return systemInfoDao.select();
    }

    @Override
    public List<SystemInfo> vagueSelect(SystemInfo systeminfo) {
        return null;
    }
}
