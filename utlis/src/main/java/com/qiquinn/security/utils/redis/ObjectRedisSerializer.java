package com.qiquinn.security.utils.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/16
 * @Modified By:
 */
public class ObjectRedisSerializer implements RedisSerializer
{
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    /**
     * 定义转换空字节数组
     */
    private static final byte[] EMPTY_ARRAY = new byte[0];

    @Nullable
    @Override
    public byte[] serialize(@Nullable Object object) throws SerializationException
    {
        byte[] byteAarry = null;
        if(null==object)
        {

            System.err.println("----------------------------->:Redis待序列化的对象为空.");
            return null;
        }
        else
        {
            try {
                byteAarry = serializer.convert(object);
            }
            catch (Exception ex)
            {
                System.out.println("========Redis序列化失败");
            }
        }
        return byteAarry;
    }

    @Nullable
    @Override
    public Object deserialize(@Nullable byte[] bytes) throws SerializationException {
        Object obj = null;
        if((null == bytes)|| (bytes.length == 0)){
            System.out.println("---------------------------------->Redis待反序列化的对象为空.");
        }else{
            try {
                obj = deserializer.convert(bytes);
            } catch (Exception e) {
                System.out.println("------------------------------------->Redis反序列化对象失败,异常："+e.getMessage());
            }
        }
        return obj;
    }
}
