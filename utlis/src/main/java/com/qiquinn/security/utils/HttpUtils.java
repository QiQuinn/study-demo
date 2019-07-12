package com.qiquinn.security.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/11
 * @Modified By:
 */
public class HttpUtils
{
    /**
      * @Author:QiQuinn
      * @Desicription:
      * @Date:Created in 2019/7/11 17:23
      * @param url 资源地址
      * @param map 参数列表
      * @param encoding 编码
      *@return java.lang.String
      * @Modified By:
      */
    public static String send(String url,Map<String,String> map,String encoding) throws  Exception,IOException
    {
        String body = "";
        CloseableHttpClient client = HttpClients.createDefault();
        //POST方式请求资源
        HttpPost httpPost = new HttpPost(url);

        JSONObject jsonObject = new JSONObject();
        //装填参数(表单提交)
//        List<NameValuePair> list = new ArrayList<NameValuePair>();
        if(map!=null)
        {
            for (Entry<String,String> entry : map.entrySet())
            {
                jsonObject.put(entry.getKey(),entry.getValue());
//                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }
        //设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(),encoding);
        stringEntity.setContentType("application/json");
        stringEntity.setContentEncoding(encoding);
        httpPost.setEntity(stringEntity);
        System.out.println( stringEntity.toString() );
//        httpPost.setEntity(new UrlEncodedFormEntity(list,encoding));
        System.out.println("url: "+url);
        System.out.println("map :"+map.toString());
        //设置hander
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
//        httpPost.setHeader("Content-type", "application/json");
//        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //执行请求结果并且得到结果(同步阻塞)
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if(entity!=null)
        {
            body = EntityUtils.toString(entity,encoding);
        }
        EntityUtils.consume(entity);
        //释放资源
        response.close();
        return body;
    }
}
