package com.example.util;

import com.alibaba.fastjson.JSONObject;
import com.example.constant.Constants;
import com.example.vo.Result;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * @Desc HttpClientUtils
 * @Author ZhangChunjie
 * @Date 2020/1/19 14:23
 * @Version 1.0
 */
public class HttpClientUtils {

    private static RequestConfig requestConfig;

    private static HttpClientBuilder clientBuilder;

    private static PoolingHttpClientConnectionManager connectionManager;

    private static int MAX_CONNECTION = 10;

    private static int DEFAULT_MAX_CONNECTION = 5;

    private static String IP = "www.gdni.com";

    private static Integer PORT = 80;

    static {
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000).build();

        HttpHost target = new HttpHost(IP, PORT);
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_CONNECTION);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTION);
        connectionManager.setMaxPerRoute(new HttpRoute(target), 20);
        clientBuilder = HttpClients.custom();
        clientBuilder.setConnectionManager(connectionManager);
    }

    public static CloseableHttpClient getConnection() {
        return clientBuilder.build();
    }

    public static Result upload(String url, MultipartFile file, Map<String, String> header) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        Set<Map.Entry<String, String>> entrySet = header.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName("UTF-8"));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        String filename = file.getOriginalFilename();
        builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, filename);
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        httpPost.setConfig(requestConfig);
        CloseableHttpClient httpClient = getConnection();
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        int code = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(responseEntity, java.nio.charset.Charset.forName("UTF-8"));
//        httpClient.close();
        return Result.create(Constants.CODE,code).set(Constants.MSG,responseEntity);
    }

    public static void main(String args[]) throws IOException {

    }

}
