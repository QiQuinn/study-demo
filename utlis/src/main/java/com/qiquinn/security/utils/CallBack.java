package com.qiquinn.security.utils;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/11
 * @Modified By:
 */
public interface CallBack {
    Map<String,Object> onError();
    Map<String,Object> onSucces();
}
