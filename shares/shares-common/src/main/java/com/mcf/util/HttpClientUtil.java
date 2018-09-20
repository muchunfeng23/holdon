package com.mcf.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcf.exception.SystemException;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientUtil {

	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public static final int DEFAULT_SOCKET_TIMEOUT = 5000;
	public static final int DEFAULT_CONNECT_TIMEOUT = 5000;
	public static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 5000;

	private static CloseableHttpClient httpClient = HttpClients.createDefault();

	/**
	 * post请求
	 * 
	 * @author lichao 2015年12月19日
	 * @param url
	 * @return
	 */
	public static String post(String url) {
		return post(url, null);
	}

	/**
	 * post请求
	 * 
	 * @author lichao 2015年12月19日
	 * @param url
	 * @param param
	 * @return
	 */
	public static String post(String url, Map<String, String> param) {
		return post(
				url,
				param,
				RequestConfig
						.custom()
						.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
						.setConnectTimeout(
								HttpClientUtil.DEFAULT_CONNECT_TIMEOUT)
						.setConnectionRequestTimeout(
								DEFAULT_CONNECTION_REQUEST_TIMEOUT).build());
	}

	/**
	 * post请求
	 * 
	 * @author lichao 2015年12月19日
	 * @param url
	 * @param param
	 * @param requestConfig
	 * @return
	 */
	public static String post(String url, Map<String, String> param,
			RequestConfig requestConfig) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		String responseString = null;
		if (null != param && param.size() > 0) {
			Set<String> keySet = param.keySet();
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : keySet) {
				nvps.add(new BasicNameValuePair(key, param.get(key)));
			}
			try {
				log.info("set utf-8 form entity to httppost");
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8
						.name()));
			} catch (UnsupportedEncodingException e) {
				throw new SystemException(e.getMessage(), e);
			}
		}
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.warn("response is not closed");
				throw new SystemException(e.getMessage(), e);
			}
		}
		return responseString;
	}

	/**
	 * json方式发送post请求
	 * 
	 * @author lichao 2015年12月20日
	 * @param url
	 * @return
	 */
	public static String postJson(String url) {
		return postJson(url, null);
	}

	/**
	 * json方式发送post请求
	 * 
	 * @author lichao 2015年12月20日
	 * @param url
	 * @param jsonEntity
	 * @return
	 */
	public static String postJson(String url, String jsonEntity) {
		return postJson(
				url,
				jsonEntity,
				RequestConfig
						.custom()
						.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
						.setConnectTimeout(
								HttpClientUtil.DEFAULT_CONNECT_TIMEOUT)
						.setConnectionRequestTimeout(
								DEFAULT_CONNECTION_REQUEST_TIMEOUT).build());
	}

	/**
	 * json方式发送post请求
	 * 
	 * @author lichao 2015年12月20日
	 * @param url
	 * @param jsonEntity
	 * @param requestConfig
	 * @return
	 */
	public static String postJson(String url, String jsonEntity,
			RequestConfig requestConfig) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		httpPost.addHeader("Connection", "Keep-Alive");
		httpPost.addHeader("user-agent", "MEICAI/MC");
		CloseableHttpResponse response = null;
		String responseString = null;
		if (jsonEntity != null) {
			httpPost.setEntity(new StringEntity(jsonEntity, Consts.UTF_8.name()));
		}
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.warn("response is not closed");
				throw new SystemException(e.getMessage(), e);
			}
		}
		return responseString;
	}

	public static String get(String url) {
		String result = "";
		try {
			// 用get方法发送http请求
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse httpResponse = null;
			// 发送get请求
			httpResponse = httpClient.execute(get);
			try {
				// response实体
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					result = EntityUtils.toString(entity);
				}
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String postImg(String fileServer, String imgFilePath) {
		String responseString = null;
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(fileServer);// Post Request to
														// specified URL
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		FileBody fileBody = new FileBody(new File(imgFilePath));
		builder.addPart(imgFilePath, fileBody);
		postRequest.setEntity(builder.build());// adding request entity to post
												// request
		try {
			response = httpClient.execute(postRequest);
			System.out.println(response.getStatusLine().toString());
			responseString = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.warn("response is not closed");
				throw new SystemException(e.getMessage(), e);
			}
		}
		return responseString;

	}
	/*
	 * public static void postImg(String fileServer,String imgFilePath){
	 * PropertiesFileUtils profile = PropertiesFileUtils.getInstance(); String
	 * fileServer_url = profile.getPropertyValue("prop/application.properties",
	 * "fileServer_url"); String imgFilePath_url =
	 * profile.getPropertyValue("prop/application.properties",
	 * "imgFilePath_url"); if(null==fileServer||fileServer==""){ fileServer =
	 * fileServer_url; } if(null==imgFilePath||imgFilePath==""){ imgFilePath =
	 * imgFilePath_url; } String targetURL = null;// TODO 指定URL File targetFile
	 * = null;// TODO 指定上传文件
	 * 
	 * targetFile = new File(imgFilePath_url); targetURL = fileServer_url;
	 * //servleturl PostMethod filePost = new PostMethod(targetURL);
	 * 
	 * try {
	 * 
	 * //通过以下方法可以模拟页面参数提交 //filePost.setParameter("name", "中文");
	 * //filePost.setParameter("pass", "1234");
	 * 
	 * Part[] parts = { new FilePart(targetFile.getName(), targetFile) };
	 * filePost.setRequestEntity(new
	 * MultipartRequestEntity(parts,filePost.getParams())); HttpClient client =
	 * new HttpClient();
	 * client.getHttpConnectionManager().getParams().setConnectionTimeout
	 * (500000); int status = client.executeMethod(filePost);
	 * System.out.println(filePost.getResponseBodyAsString()); if (status ==
	 * HttpStatus.SC_CREATED) { System.out.println("上传成功"); // 上传成功 } else {
	 * System.out.println("上传失败"); // 上传失败 } } catch (Exception ex) {
	 * ex.printStackTrace(); } finally { filePost.releaseConnection(); } }
	 */
}
