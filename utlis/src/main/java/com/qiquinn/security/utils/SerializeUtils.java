package com.qiquinn.security.utils;

import com.qiquinn.security.utils.Exceptions.CustomerExpection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:QiQuinn
 * @Desicription: 序列化工具
 * @Date:Created in 2019/7/15
 * @Modified By:
 */
public class SerializeUtils
{
    private static Logger log = LoggerFactory.getLogger(SerializeUtils.class);

    public static byte[] serialize(Object object)
    {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        byte[] obj_bytes = null;
        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            obj_bytes = baos.toByteArray();
        }
        catch (IOException IoException)
        {
            log.error("序列化失败: "+IoException);
        }
        finally
        {
            try {
                if (oos != null)
                {
                    oos.close();
                }
                if (baos != null)
                {
                    baos.close();
                }
            }
            catch (Exception e2)
            {
                log.error("关流错误！： "+e2);
                e2.printStackTrace();
            }
        }
        return obj_bytes;
    }

    public static <T> byte[] serialize(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv=null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            for(T obj : list){
                os.writeObject(obj);
            }
            os.writeObject(null);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("Non-serializable object", e);
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }

    public static Object unSerialize(byte[] bytes)
    {
        Object obj = null;
        try {
            // 反序列化
            final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            final ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
            ois.close();
            bais.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static <T> List<T> unserializeForList(byte[] bytes) {
        List<T> list = new ArrayList<T>();
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if(bytes != null) {
                bis=new ByteArrayInputStream(bytes);
                is=new ObjectInputStream(bis);
                while (true) {
                    T obj = (T) is.readObject();
                    if(obj == null){
                        break;
                    }else{
                        list.add(obj);
                    }
                }
                is.close();
                bis.close();
            }
        } catch (IOException e) {
            log.warn("Caught IOException decoding %d bytes of data",
                    bytes == null ? 0 : bytes.length, e);
        } catch (ClassNotFoundException e) {
            log.warn("Caught CNFE decoding %d bytes of data",
                    bytes == null ? 0 : bytes.length, e);
        } finally {
            close(is);
            close(bis);
        }
        return list;
    }


    /**
     * 关闭的数据源或目标。调用 close()方法可释放对象保存的资源（如打开文件）
     * 关闭此流并释放与此流关联的所有系统资源。如果已经关闭该流，则调用此方法无效。
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                log.info("Unable to close %s", closeable, e);
            }
        }
    }
}
