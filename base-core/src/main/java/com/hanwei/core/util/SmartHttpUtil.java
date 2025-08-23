package com.hanwei.core.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import org.apache.commons.lang3.StringUtils;


import org.apache.http.Consts;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



/**
 * @Description SmartHttpUtil (原老方式，后期处理 TODO)
 * @Date 2024/1/17 15:25
 * @Since version-1.0
 */
public class SmartHttpUtil {

    /**
     * 方法描述: 发送get请求
     *
     * @param url
     * @param params
     * @param header
     * @Return {@link String}
     * @throws
     * @date 2020年07月27日 09:10:10
     */
    public static String sendGet(String url, Map<String, String> params, Map<String, String> header) throws Exception {
        HttpGet httpGet = null;
        String body = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            List<String> mapList = new ArrayList<>();
            if (params != null) {
                for (Entry<String, String> entry : params.entrySet()) {
                    mapList.add(entry.getKey() + "=" + entry.getValue());
                }
            }
            if (CollectionUtils.isNotEmpty(mapList)) {
                url = url + "?";
                String paramsStr = StringUtils.join(mapList, "&");
                url = url + paramsStr;
            }
            httpGet = new HttpGet(url);
            httpGet.setHeader("Content-type", "application/json; charset=utf-8");
            httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if (header != null) {
                for (Entry<String, String> entry : header.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse response = httpClient.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("请求失败");
            } else {
                body = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
        return body;
    }

    /**
     * 方法描述: 发送post请求-json数据
     * @param url
     * @param json
     * @param CmpToken
     * @throws
     */
    public static String sendPostJson(String url, JSONObject json, String CmpToken) throws Exception {
        HttpPost httpPost = null;
        String body = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

            httpPost.setHeader("cmp_token", CmpToken);

            StringEntity entity = new StringEntity(json.toString(), StandardCharsets.UTF_8);

            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("请求失败");
            } else {
                body = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return body;
    }

    /**
     * post请求,发送json数据
     *
     * @param url
     * @param header
     * @return
     */
    public static JSONObject doPost(String url,Map<String, String> header) {
        JSONObject response = null;
        try {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if (header != null) {
                for (Entry<String, String> entry : header.entrySet()) {
                    post.setHeader(entry.getKey(), entry.getValue());
                }
            }

            StringEntity s = new StringEntity("{\"SID\":\"SGUIAABSUIYU\"}", "UTF-8"); // 中文乱码在此解决
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = HttpClients.createDefault().execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSON.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 方法描述: 发送post请求-form表单数据
     * @param url
     * @param header
     * @throws
     */
    public static String sendPostForm(String url, Map<String, String> params, Map<String, String> header) throws Exception {
        HttpPost httpPost = null;
        String body = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if (header != null) {
                for (Entry<String, String> entry : header.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            List<BasicNameValuePair> nvps = new ArrayList<>();
            if (params != null) {
                for (Entry<String, String> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            //设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("请求失败");
            } else {
                body = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return body;
    }



    /**
     * 方法描述: 发送post请求-.net core数据
     * @param url
     * @throws
     */
    public static String sendPost(String url) throws Exception {
        HttpPost httpPost = null;
        String body = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //httpPost.setHeader("Transfer-Encoding", "chunked");
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");


            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("请求失败");
            } else {
                body = EntityUtils.toString(response.getEntity(), "UTF-8");
//直接接收到的字符串是转义后的，先转成json对象，再转为字符串，解决此问题
               JSONObject J =JSONObject.parseObject(body);
               body=J.toJSONString();

            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return body;
    }

    public static String sendPostData(String url, Map<String, String> header, Map<String, String> params, String jsonParam) {
        // 验证url 的合法性 符合http 或http规范
        url = UriComponentsBuilder.fromHttpUrl(url).toUriString();
        String resultStr = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.custom().setSSLSocketFactory(
                            new SSLConnectionSocketFactory(
                                    //忽略掉对服务器端证书的校验
                                    SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build()
                                    , NoopHostnameVerifier.INSTANCE))
                    .build();
            HttpPost httpPost = new HttpPost(url);
            //配置请求信息
            //httpPost.setConfig(configRequest());
            //设置header
            if (header != null && header.size() > 0) {
                for (Entry<String, String> stringStringEntry : header.entrySet()) {
                    httpPost.setHeader(stringStringEntry.getKey(), stringStringEntry.getValue());
                }
            }
            //设置params
            if (params != null && params.size() > 0) {
                List<BasicNameValuePair> paramList = new ArrayList<>();
                for (Entry<String, String> stringStringEntry : params.entrySet()) {
                    paramList.add(new BasicNameValuePair(stringStringEntry.getKey(), stringStringEntry.getValue()));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramList, Consts.UTF_8);
                httpPost.setEntity(urlEncodedFormEntity);
            }

            //jsonParam
            if (!StringUtils.isEmpty(url)) {
                StringEntity entity = new StringEntity(jsonParam, Consts.UTF_8);
                httpPost.setEntity(entity);
            }

            //发起请求
            httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                resultStr = EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8);
//                logger.info("Https Post Response:" + resultStr);
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                HeaderIterator headerIterator = httpResponse.headerIterator();
                while (headerIterator.hasNext()) {
                    stringBuffer.append("\t" + headerIterator.next());
                }
                resultStr = stringBuffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("post 请求错误：" + e);
        } finally {
           // closeConnection(httpClient, httpResponse);
        }
        return resultStr;
    }


}
