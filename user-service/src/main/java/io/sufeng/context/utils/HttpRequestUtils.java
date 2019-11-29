package io.sufeng.context.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author zhangchao
 * @Date 2019/7/1 16:59
 * @Version v1.0
 */
public class HttpRequestUtils {
    public static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    public static String get(String url){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse httpResponse =  client.execute(httpGet);
            InputStream content = httpResponse.getEntity().getContent();
            String response = new String(StreamUtils.copyToByteArray(content));
            logger.info("{}",response);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String post(String url, String body){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(body));
            CloseableHttpResponse httpResponse =  client.execute(httpPost);
            InputStream content = httpResponse.getEntity().getContent();
            String response = new String(StreamUtils.copyToByteArray(content));
            logger.info("{}",response);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
