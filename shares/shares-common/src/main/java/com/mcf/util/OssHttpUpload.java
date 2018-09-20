package com.mcf.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class OssHttpUpload {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    public static final int DEFAULT_SOCKET_TIMEOUT = 5000;
    public static final int DEFAULT_CONNECT_TIMEOUT = 5000;
    public static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 5000;
    public static final int HttpClientUtil_DEFAULT_CONNECT_TIMEOUT = 5000;
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    // 鍒涘缓header
    public static Map createOssHeader(String tag, String key, long expires, long uid, String uname, String object, JSONArray options) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // token 鐢∕D5鍔犲瘑
//        MessageDigest md5=MessageDigest.getInstance("MD5");
//        md5.update((tag+key+expires).getBytes("utf-8"));
//        String token = new BigInteger(1, md5.digest()).toString(16);
//        String token = MD5UtilElse.getMD5(tag + key + expires).toLowerCase();
        String token = MD5Util.MD5(tag + key + expires, "").toLowerCase();

        // 鍒涘缓 headers
        Encoder urlBase64Encoder = Base64.getUrlEncoder();

        Map http_oss_auth_map = new HashMap();
        http_oss_auth_map.put("tag", tag);
        http_oss_auth_map.put("expires", expires);
        http_oss_auth_map.put("token", token);
        String HTTP_OSS_AUTH = urlBase64Encoder.encodeToString(JSON.toJSONString(http_oss_auth_map).getBytes("UTF-8"));
        Map http_oss_param_map = new HashMap();
        http_oss_param_map.put("uid", uid);
        http_oss_param_map.put("uname", uname);
        http_oss_param_map.put("object", object);
        http_oss_param_map.put("options", options);
        String HTTP_OSS_PARAM = urlBase64Encoder.encodeToString(JSON.toJSONString(http_oss_param_map).getBytes("UTF-8"));

        Map extraHeader = new HashMap<String, String>();
        extraHeader.put("Oss-Auth", HTTP_OSS_AUTH);
        extraHeader.put("Oss-Param", HTTP_OSS_PARAM);
        return extraHeader;
    }

    // 浼爏tring
    public static String contentUpload(String url, String content, Map<String, String> extraHeader) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(HttpClientUtil.DEFAULT_CONNECT_TIMEOUT).setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT).build());
        httpPost.addHeader("Connection", "Keep-Alive");
        httpPost.addHeader("user-agent", "MEICAI/WMC");
        for (String key : extraHeader.keySet()) {
            httpPost.addHeader(key, extraHeader.get(key));
        }
        CloseableHttpResponse response = null;
        String responseString = null;
        if (content != null) {
            // String entity
            httpPost.setEntity(new StringEntity(content, Consts.UTF_8.name()));
        }
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.warn("response is not closed");
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return responseString;
    }

    // 浼犳枃浠�, http
    public static String fileUploadUseHttp(String url,InputStream uploadFile, Map<String, String> extraHeader) throws FileNotFoundException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(HttpClientUtil.DEFAULT_CONNECT_TIMEOUT).setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT).build());
        httpPost.addHeader("Content-Type", "application/octet-stream");
        httpPost.addHeader("Connection", "Keep-Alive");
        httpPost.addHeader("user-agent", "MEICAI/WMC");
        for (String key : extraHeader.keySet()) {
            httpPost.addHeader(key, extraHeader.get(key));
        }
        CloseableHttpResponse response = null;
        String responseString = null;
        try {
            if (uploadFile != null) {
                InputStreamEntity reqEntity = new InputStreamEntity(
                        uploadFile, ContentType.APPLICATION_JSON);
                reqEntity.setContentType("binary/octet-stream");
                reqEntity.setChunked(true);
                httpPost.setEntity(reqEntity);
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.warn("response is not closed");
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return responseString;
    }

    // 浼犳枃浠�, https
    public static String fileUploadUseHttps(String url, InputStream uploadFile, Map<String, String> extraHeader) throws FileNotFoundException {

        SSLHelper sslHelper = new SSLHelper();
        PoolingHttpClientConnectionManager pcm = sslHelper.GetPcm();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pcm)
                .setDefaultRequestConfig(RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                        .setConnectTimeout(HttpClientUtil_DEFAULT_CONNECT_TIMEOUT)
                        .setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT).build()).build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(HttpClientUtil.DEFAULT_CONNECT_TIMEOUT).setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT).build());
        httpPost.addHeader("Content-Type", "application/octet-stream");
        httpPost.addHeader("Connection", "Keep-Alive");
        httpPost.addHeader("user-agent", "MEICAI/WMC");
        for (String key : extraHeader.keySet()) {
            httpPost.addHeader(key, extraHeader.get(key));
        }
        CloseableHttpResponse response = null;
        String responseString = null;
        try {
            if (uploadFile != null) {
                InputStreamEntity reqEntity = new InputStreamEntity(
                        uploadFile, ContentType.APPLICATION_JSON);
                reqEntity.setContentType("binary/octet-stream");
                reqEntity.setChunked(true);
                httpPost.setEntity(reqEntity);
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.warn("response is not closed");
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return responseString;
    }

}