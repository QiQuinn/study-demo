package com.qiquinn.security.serviceimpl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.qiquinn.security.entity.ColumnInfo;
import com.qiquinn.security.dao.ColumnInfoDao;
import com.qiquinn.security.service.ColumnInfoService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = ColumnInfoService.class)
public class ColumnInfoServiceImpl implements ColumnInfoService {

    @Autowired
    private ColumnInfoDao columninfoDao;
    //增加
    @Override
    public void insert(ColumnInfo columninfo){
        columninfoDao.insert(columninfo);
    }
    //删除
    @Override
    public void delete(ColumnInfo columninfo){
        columninfoDao.delete(columninfo);
    }
    //修改
    @Override
    public void update(ColumnInfo columninfo){
        columninfoDao.update(columninfo);
    }
    //查询
    @Override
    public List<ColumnInfo> select(){
        return columninfoDao.select();
    }
    @Override
    public ColumnInfo byIdSelect(Integer id) {
        return columninfoDao.findById(id);
    }
}