package com.qiquinn.security.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.nustaq.serialization.FSTConfiguration;
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

    private static Kryo kryo = new Kryo();

    public SerializeUtils(){}

    static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

    /**
     * @Author:QiQuinn
     * @Desicription: FST序列化
     * @Date:Created in 2019/7/18 10:33
     * @param obj  对象
     *@return byte[]
     * @Modified By:
     */
    public static byte[] serialize(Object obj)
    {
        return configuration.asByteArray(obj);
    }

    /**
     * @Author:QiQuinn
     * @Desicription: Fst反序列化
     * @Date:Created in 2019/7/18 10:32
     * @param sec  字节数组
     *@return java.lang.Object
     * @Modified By:
     */
    public static Object unserialize(byte[] sec)
    {
        return configuration.asObject(sec);
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 使用kryo序列化
      * @Date:Created in 2019/7/18 11:29
      * @param obj  对象
      *@return byte[]
      * @Modified By:
      */
    public static byte[] kryoSerizlize(Object obj) {
        Kryo kryo = new Kryo();
        byte[] buffer = new byte[1024*8000];
        try(Output output = new Output(buffer)) {

            kryo.writeClassAndObject(output, obj);
            return output.toBytes();
        } catch (Exception ex) {
            log.error("kryoSerizlize失败！"+ex.toString(),ex);
        }
        return buffer;
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 使用kryo反序列化
      * @Date:Created in 2019/7/18 11:29
      * @param src 字节码
      *@return java.lang.Object
      * @Modified By:
      */
    public static Object kryoUnSerizlize(byte[] src) {
        try(
                Input input = new Input(src)
        ){
            return kryo.readClassAndObject(input);
        }catch (Exception ex) {
            log.error("kryoUnSerizlize失败！"+ex.toString(),ex);
        }
        return kryo;
    }

    /**
      * @Author:QiQuinn
      * @Desicription: JDK序列化
      * @Date:Created in 2019/7/18 11:29
      * @param object
      *@return byte[]
      * @Modified By:
      */
    public static byte[] jdkSerialize(Object object)
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

    /**
      * @Author:QiQuinn
      * @Desicription: JDK巨蟹话List
      * @Date:Created in 2019/7/18 11:30
      * @param list  列表
      *@return byte[]
      * @Modified By:
      */
    public static <T> byte[] JdkSerialize(List<T> list) {
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

    /**
      * @Author:QiQuinn
      * @Desicription: JDK反序列话
      * @Date:Created in 2019/7/18 11:30
      * @param bytes  字节码
      *@return java.lang.Object
      * @Modified By:
      */
    public static Object jdkUnSerialize(byte[] bytes)
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
    /**
      * @Author:QiQuinn
      * @Desicription: JDK反序列化LIST
      * @Date:Created in 2019/7/18 11:30
      * @param bytes  字节码
      *@return java.util.List<T>
      * @Modified By:
      */
    public static <T> List<T> jdkUnserializeForList(byte[] bytes) {
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
