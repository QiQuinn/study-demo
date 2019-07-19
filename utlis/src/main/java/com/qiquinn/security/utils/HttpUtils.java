package com.qiquinn.security.utils;


import com.alibaba.fastjson.JSONObject;
import com.qiquinn.security.utils.Exceptions.CustomerExpection;
import com.qiquinn.security.utils.stringenum.MessageEnum;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
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
    public static String send(String url,Map<String,Object> map,String encoding)
    {
        String body = "";
        try
        {
            CloseableHttpClient client = HttpClients.createDefault();
            //POST方式请求资源
            HttpPost httpPost = new HttpPost(url);
            JSONObject jsonObject = new JSONObject();
            //装填参数(表单提交)
//        List<NameValuePair> list = new ArrayList<NameValuePair>();
            if(map!=null)
            {
                for (Entry<String,Object> entry : map.entrySet())
                {
                    jsonObject.put(entry.getKey(),entry.getValue());
                }
            }
            //设置参数到请求对象中
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(),encoding);
            stringEntity.setContentType("application/json");
            stringEntity.setContentEncoding(encoding);
            httpPost.setEntity(stringEntity);
            //设置hander
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
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
        }
        catch (Exception ex)
        {
            throw new CustomerExpection(MessageEnum.ERROR_SENDHTTP.getCode(),
                    MessageEnum.ERROR_SENDHTTP.getMsg(),"send",ex);
        }
        return body;
    }
}
