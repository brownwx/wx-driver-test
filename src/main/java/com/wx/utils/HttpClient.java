package com.wx.utils;


import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import static com.wx.variable.WxVariable.NPro;

/**
 * @author wuxi
 * @date 2019年1月14日
 */
public class HttpClient {


    public  CloseableHttpClient httpClient = HttpClients.createDefault();

    public  void HttpPostParm() throws  IOException {

        HttpPost post = new HttpPost(NPro.getKey("startUrl"));

        List<NameValuePair> paramList = new ArrayList<>();// 将请求数据加入到此list中
        paramList.add(new BasicNameValuePair("username", NPro.getKey("startUrl")));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, Charset.forName("utf-8"));

        // 根据数据构造请求body部分
        post.setEntity(entity);
        CloseableHttpResponse res = httpClient.execute(post);// 发送请求
        try {
            int statusCode = res.getStatusLine().getStatusCode();
            // 得到响应的状态码
            System.out.println(statusCode);
            // 得到响应的内容
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);
        } finally {
            res.close();
        }

        //httpClient.close();
    }


    public  void HttpPostJson(String requestContent,String url) throws IOException {
        HttpPost post = new HttpPost(url);
        //String requestContent = NServer.getKey("user");
        StringEntity entity = new StringEntity(requestContent, "utf-8");
        // 如果是xml或json请求数据，那么通过StringEntity构造请求数据，同时设置字符集
        entity.setContentType("application/json");// 告诉服务器请求内容的类型
        post.setEntity(entity);
        CloseableHttpResponse res = httpClient.execute(post);// 发送请求
        try {
            int statusCode = res.getStatusLine().getStatusCode();
            //System.out.println(statusCode);
            String resBody = EntityUtils.toString(res.getEntity());
            //System.out.println(resBody);
        } finally {
            res.close();
        }

        //httpClient.close();
    }

    public void closeHttp() throws IOException {
        httpClient.close();
    }

}
