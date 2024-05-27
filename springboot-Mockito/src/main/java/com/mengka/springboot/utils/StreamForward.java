package com.mengka.springboot.utils;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;


/**
 * @author: [wujie]
 * @createTime: [2019/7/1 9:03]
 * @description: 文件流中转 因为httpclient对https证书错误时处理不好，所以用url实现
 */
public class StreamForward {
    int buffSize = 1024 * 2;

    public StreamForward() {
    }

    static class X509TrustUtiil implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)  {
            // TODO Auto-generated method stub

        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)  {
            // TODO Auto-generated method stub

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }

    }


    public StreamForward(int buffSize) {
        this.buffSize = buffSize;
    }

    /**
     * 转发https
     *
     * @param urls
     * @param response
     * @throws Exception
     */
    public void forwarOutputHttps(String urls, HttpServletResponse response, String range) throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        sslcontext.init(null, new TrustManager[]{new X509TrustUtiil()}, new java.security.SecureRandom());
        URL url = new URL(urls);
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslsession) {

                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
        if (range != null) {
            urlCon.setRequestProperty("Range", range);
        }
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        int code = urlCon.getResponseCode();
        setFileds(urlCon, response);
        if (response != null) {
            response.setStatus(code);

            // 读文件流
            DataInputStream in = new DataInputStream(urlCon.getInputStream());
            String fileName = urls.substring(urls.lastIndexOf('/'), urls.length());
            //正则截取文件
            fileName = fileName.replaceAll("(^.*?\\.(wav|mp3)).*$", "$1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            write(response.getOutputStream(), in);
        }
    }


    public void setFileds(URLConnection conn, HttpServletResponse response) {
        if(response!=null) {
            Map<String, List<String>> headerFields = conn.getHeaderFields();
            headerFields.forEach((k, v) -> {
                if (v.size() == 1) {
                    response.setHeader(k, v.get(0));
                }
            });
        }
    }

    public void forwarOutputHttp(String urls, HttpServletResponse response, String range) throws Exception {
        URL url = new URL(urls);
        HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
        if (range != null) {
            urlCon.setRequestProperty("Range", range);
        }
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        int code = urlCon.getResponseCode();

        setFileds(urlCon, response);
        if (response != null) {
            response.setStatus(code);
            // 读文件流
            DataInputStream in = new DataInputStream(urlCon.getInputStream());
            String fileName = urls.substring(urls.lastIndexOf('/'), urls.length());
            //正则截取文件
            fileName = fileName.replaceAll("(^.*?\\.(wav|mp3)).*$", "$1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            write(response.getOutputStream(), in);
        }
    }

    private void write(OutputStream out, InputStream in) throws Exception {


        int count = 0;

        byte[] buffer = new byte[buffSize];
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();
        in.close();
    }

    /**
     * @param urls     请求的URL
     * @param response 输出的流
     * @throws Exception
     */
    public void forwardOutput(String urls, HttpServletResponse response, String range) throws Exception {
        if (urls.startsWith("https:")) {
            forwarOutputHttps(urls, response, range);
        } else {
            forwarOutputHttp(urls, response, range);
        }
    }


}