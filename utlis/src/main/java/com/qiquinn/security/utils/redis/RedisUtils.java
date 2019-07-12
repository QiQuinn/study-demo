package com.qiquinn.security.utils.redis;

import com.qiquinn.security.utils.Exceptions.CustomerExpection;
import com.qiquinn.security.utils.Exceptions.MessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author:QiQuinn
 * @Desicription: Redis操作
 * @Date:Created in 2019/7/11
 * @Modified By:
 */
@Component
public class RedisUtils
{
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
      * @Author:QiQuinn
      * @Desicription: 判断Key是否存在
      * @Date:Created in 2019/7/11 11:18
      * @param key  键值
      *@return boolean 是or否
      * @Modified By:
      */
    public boolean hashkey(String key)
    {
        try {
            return redisTemplate.hasKey(key);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 删除key对应的值
      * @Date:Created in 2019/7/11 11:16
      * @param key 键值
      * @Modified By:
      */
    public void delete(String... key)
    {
        if(key!=null && key.length>0)
        {
            if(key.length==1)
            {
                redisTemplate.delete(key[0]);
            }
            else
            {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 获取对应的值
      * @Date:Created in 2019/7/11 11:16
      * @param key 键值
      *@return java.lang.Object
      * @Modified By:
      */
    public Object getObject(String key)
    {
        return redisTemplate.opsForValue().get(key);
    }
    /**
     * @Author:QiQuinn
     * @Desicription: 普通存入缓，并设置时间
     * @Date:Created in 2019/7/11 11:14
     * @param key 键值
     * @param value 值
     *@return boolean
     * @Modified By:
     */
    public boolean setObject(String key,Object value)
    {
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 普通存入缓，并设置时间
      * @Date:Created in 2019/7/11 11:14
      * @param key 键值
    	 * @param value 值
    	 * @param time 时间
      *@return boolean
      * @Modified By:
      */
    public boolean setObject(String key,Object value,long time)
    {
        try
        {
            if(time>0)
            {
                redisTemplate.opsForValue().set(key,value,time);
            }
            else
            {
                setObject(key,value);
            }
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 递增
      * @Date:Created in 2019/7/11 11:13
      * @param key 键值
    	 * @param delta 递增值
      * @Modified By:
      */
    public long incr(String key,long delta)
    {
        if(delta < 0)
        {
            throw new CustomerExpection(MessageEnum.ERROR_RIDES_INCREASE.getCode(),
                    MessageEnum.ERROR_RIDES_INCREASE.getMsg(),"incr",this);
        }
        else
        {
            return redisTemplate.opsForValue().increment(key,delta);
        }
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 递减
      * @Date:Created in 2019/7/11 11:12
      * @param key 值
    	 * @param delta 降序的值
      *@return long
      * @Modified By:
      */

    public long drr(String key,long delta)
    {
        if(delta <0 )
        {
            throw new CustomerExpection(MessageEnum.ERROR_RIDES_DDR.getCode(),
                    MessageEnum.ERROR_RIDES_DDR.getMsg(),"drr",this);
        }
        else
        {
            return redisTemplate.opsForValue().increment(key,-delta);
        }
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 获取键值
      * @Date:Created in 2019/7/11 11:11
      * @param key 键值
      * @param item 项
      *@return java.lang.Object
      * @Modified By:
      */
    public Object getHash(String key, String item)
    {
        return redisTemplate.opsForHash().get(key, item);
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 获取key所对应的所有键值
      * @Date:Created in 2019/7/11 11:11
      * @param key 键值
      *@return java.util.Map<java.lang.Object,java.lang.Object>
      * @Modified By:
      */
    public Map<Object, Object> getAllHashByKey(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
    /**
     * @Author:QiQuinn
     * @Desicription: 放入数据，
     * @Date:Created in 2019/7/11 11:08
     * @param key 键值
     * @param map 数据
     *@return boolean 是否成功
     * @Modified By:
     */
    public boolean setHashMap(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 放入数据，
      * @Date:Created in 2019/7/11 11:08
      * @param key 键值
    	 * @param map 数据
    	 * @param time  时间
      *@return boolean 是否成功
      * @Modified By:
      */
    public boolean setHashMap(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * @Author:QiQuinn
     * @Desicription: 向一张hash表中放入数据,如果不存在将创建
     * @Date:Created in 2019/7/11 10:58
     * @param key 键值
     * @param item 项
     * @param value 值
     *@return boolean 成功or失败
     * @Modified By:
     */
    public boolean setHash(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 向一张hash表中放入数据,如果不存在将创建
      * @Date:Created in 2019/7/11 10:58
      * @param key 键值
    	 * @param item 项
    	 * @param value 值
    	 * @param time 时间
      *@return boolean 成功or失败
      * @Modified By:
      */
    public boolean setHash(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
      * @Author:QiQuinn
      * @Desicription: hash表中是否有改项的值
      * @Date:Created in 2019/7/11 10:54
      * @param key 键值
    	 * @param item 项
      *@return boolean 是or否
      * @Modified By:
      */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 删除hash中的值
      * @Date:Created in 2019/7/11 10:53
      * @param key 键值
    	 * @param item 值(可以多个)
      * @Modified By:
      */
    public void deleteHash(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }
    /**
      * @Author:QiQuinn
      * @Desicription: hash递减
      * @Date:Created in 2019/7/11 10:52
      * @param key 键值
    	 * @param item 项
    	 * @param by  减少多少(大于0)
      *@return double 结果
      * @Modified By:
      */
    public double increamentHash(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 根据key获取set中所有值
      * @Date:Created in 2019/7/11 10:51
      * @param key  键值
      *@return java.util.Set<java.lang.Object> 结果
      * @Modified By:
      */
    public Set<Object> getSetByKey(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 根据value从一个set中查找是否存在
      * @Date:Created in 2019/7/11 10:48
      * @param key 键值
    	 * @param value 值
      *@return boolean true 存在 false不存在
      * @Modified By:
      */
    public boolean existSetByValue(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 存入set缓存
      * @Date:Created in  10:47
      * @param key 键值
	 * @param values 存入的值（可多个）
      *@return long 存入多少个
      * @Modified By:
      */
    public long saveSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 设置set(带有时间)
      * @Date:Created in 2019/7/11 10:42
      * @param key 键值
    	 * @param time 时效性时间
    	 * @param values 值
      *@return long 成功个数
      * @Modified By:
      */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
            expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
      * @Author:QiQuinn
      * @Desicription:获取set缓存的长度
      * @Date:Created in 2019/7/11 10:41
      * @param key 键值
      *@return long 长度
      * @Modified By:
      */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 移除值为values的key
      * @Date:Created in 2019/7/11 10:40
      * @param key 键值
      * @param values 数据（可以传多个）
      *@return long 移除的个数
      * @Modified By:
      */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // ====================== commone ======================
    /**
      * @Author:QiQuinn
      * @Desicription: 设置缓存失效的时间
      * @Date:Created in 2019/7/11 10:45
      * @param key 键值
      * @param time  时效性
      *@return boolean 是否成功
      * @Modified By:
      */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 获取缓存失效时间
      * @Date:Created in 2019/7/11 10:46
      * @param key 键值
      *@return long 失效时间
      * @Modified By:
      */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
