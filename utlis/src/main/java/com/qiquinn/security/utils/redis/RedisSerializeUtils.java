package com.qiquinn.security.utils.redis;

import com.qiquinn.security.utils.SerializeUtils;
import com.qiquinn.security.utils.stringenum.RedisSaveStringConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author:QiQuinn
 * @Desicription: Redis操作
 * @Date:Created in 2019/7/11
 * @Modified By:
 */
@Component
public class RedisSerializeUtils
{
    @Resource
    private RedisTemplate<String,byte[]> redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisSerializeUtils.class);
    /**
      * @Author:QiQuinn
      * @Desicription: 对象序列化存储<String,byte[]>
      * @Date:Created in 2019/7/17 18:02
      * @param key 键值
      * @param obj 对象
      * @param time 遗忘时间
      *@return void
      * @Modified By:
      */
    public void setObject(final String key,Object obj,long time)
    {
        setObjcetBytes(key, SerializeUtils.serialize(obj),time);
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 默认时间序列换存储 60s
      * @Date:Created in 2019/7/18 9:37
      * @param key 键值
      * @param obj 类型
      *@return void
      * @Modified By:
      */
    public void setObject(final String key,Object obj)
    {
        setObjcetBytes(key,SerializeUtils.serialize(obj), RedisSaveStringConfig.DEFUALT_SAVETIME);
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 获取对象
      * @Date:Created in 2019/7/17 18:03
      * @param key 键值
      *@return java.lang.Object
      * @Modified By:
      */
    public Object getObject(final String key)
    {
        if(key==null)
        {
            return null;
        }
        else
        {
            return SerializeUtils.unserialize(getObjectBytes(key));
        }
    }

    /**
     * @Author:QiQuinn
     * @Desicription: 序列化对象
     * @Date:Created in 2019/7/17 18:03
     * @param key 键值
     * @param object 对象
     * @param time  遗忘时间
     *@return boolean
     * @Modified By:
     */
    private boolean setObjcetBytes(String key,byte[] object,long time)
    {
        try
        {
            redisTemplate.opsForValue().set(key, object,time,TimeUnit.SECONDS);
            return true;
        }
        catch (Exception ex)
        {
            log.error("redis存储Object失败！"+ex.toString(),ex);
            return false;
        }
    }
    /**
     * @Author:QiQuinn
     * @Desicription: 反序列化对象
     * @Date:Created in 2019/7/17 18:04
     * @param key  键值
     *@return byte[]
     * @Modified By:
     */
    private byte[] getObjectBytes(String key)
    {
        byte[] result = null;
        if (key==null)
            return null;
        try
        {
            result = redisTemplate.opsForValue().get(key);
        }
        catch (Exception ex)
        {
            log.error("redis获取Object失败！"+ex.toString(),ex);
        }
        return result;
    }
}
