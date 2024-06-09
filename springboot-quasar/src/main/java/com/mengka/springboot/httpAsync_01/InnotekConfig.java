package com.mengka.springboot.httpAsync_01;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018-03-12
 * @since cabbage-forward2.0
 */
public class InnotekConfig {

    private static String baseUrl = "https://home.mozu.com";
    private static String basePciUrl = "https://pmts.mozu.com";
    private static String encodeAlgorithm = "SHA-256";
    private static String charSet = "UTF-8";
    private static int defaultEventRequestTimeout = 180;
    private static String proxyHost = null;
    private static Integer proxyPort = null;
    private static Integer defaultHttpClientMaxPerRoute = 700;//32768
    private static Integer maxHttpClientConnections = 1000;//65072

    /**
     * connectionRequestTimeout: 从连接池中获取连接的超时时间
     * connectTimeout: 连接上服务器(握手成功)的时间
     * socketTimeout: 服务器返回数据(response)的时间
     */
    private static Integer httpClientTimeoutMillis = 60000;

    private static Integer httpConnectionRequestTimeout = 120000;//从连接池中获取连接的超时时间

    /**
     * Get the proxy port if one is set. This is deprecated and no longer sets
     * the proxy port in Mozu Client. Set the system variables using command
     * line: <code>Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=8888</code>
     * <code>Dhttps.proxyHost=127.0.0.1 -Dhttps.proxyPort=8888</code>
     * <p>
     * or this code: <code>
     * System.setProperty("http.proxyHost", "127.0.0.1");
     * System.setProperty("https.proxyHost", "127.0.0.1");
     * System.setProperty("http.proxyPort", "8888");
     * System.setProperty("https.proxyPort", "8888");
     * </code>
     *
     * @return the proxy Port
     */
    @Deprecated
    public static Integer getProxyPort() {
        return proxyPort;
    }

    /**
     * Set the proxy port
     *
     * @param proxyPort
     */
    @Deprecated
    public static void setProxyPort(Integer proxyPort) {
        InnotekConfig.proxyPort = proxyPort;
    }

    /**
     * This is deprecated and no longer sets the proxy port in Mozu Client. Set
     * the system variables using command line:
     * <code>Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=8888</code>
     * <code>Dhttps.proxyHost=127.0.0.1 -Dhttps.proxyPort=8888</code>
     * <p>
     * or this code: <code>
     * System.setProperty("http.proxyHost", "127.0.0.1");
     * System.setProperty("https.proxyHost", "127.0.0.1");
     * System.setProperty("http.proxyPort", "8888");
     * System.setProperty("https.proxyPort", "8888");
     * </code>
     *
     * @return
     */
    @Deprecated
    public static String getProxyHost() {
        return proxyHost;
    }

    @Deprecated
    public static void setProxyHost(String proxyHost) {
        InnotekConfig.proxyHost = proxyHost;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        InnotekConfig.baseUrl = baseUrl;
    }

    public static String getBasePciUrl() {
        return basePciUrl;
    }

    public static void setBasePciUrl(String basePciUrl) {
        InnotekConfig.basePciUrl = basePciUrl;
    }

    public static String getEncodeAlgorithm() {
        return encodeAlgorithm;
    }

    public static void setEncodeAlgorithm(String encodeAlgorithm) {
        InnotekConfig.encodeAlgorithm = encodeAlgorithm;
    }

    public static String getCharSet() {
        return charSet;
    }

    public static void setCharSet(String charSet) {
        InnotekConfig.charSet = charSet;
    }

    public static int getDefaultEventRequestTimeout() {
        return defaultEventRequestTimeout;
    }

    public static void setDefaultEventRequestTimeout(int defaultEventRequestTimeout) {
        InnotekConfig.defaultEventRequestTimeout = defaultEventRequestTimeout;
    }

    /**
     * Get the max number of clients that can re-use a HttpClient connection
     * route. The default is 20.
     *
     * @return the number of clients that can use the HttpClient connection
     * route.
     */
    public static Integer getDefaultHttpClientMaxPerRoute() {
        return defaultHttpClientMaxPerRoute;
    }

    /**
     * Set the max number of clients that can re-use a HttpClient connection
     * route. The default is 20.
     *
     * @param defaultHttpClientMaxPerRoute
     */
    public static void setDefaultHttpClientMaxPerRoute(Integer defaultHttpClientMaxPerRoute) {
        InnotekConfig.defaultHttpClientMaxPerRoute = defaultHttpClientMaxPerRoute;
    }

    /**
     * Get the maximum number of pooled HttpClient connections.
     *
     * @return the max number of pooled HttpClient connections. Default is 200.
     */
    public static Integer getMaxHttpClientConnections() {
        return maxHttpClientConnections;
    }

    /**
     * Set the maximum number of pooled HttpClient connections.
     *
     * @param setMaxHttpClientConnections
     */
    public static void setMaxHttpClientConnections(Integer setMaxHttpClientConnections) {
        InnotekConfig.maxHttpClientConnections = setMaxHttpClientConnections;
    }

    /**
     * Return the Http timeout wait time in milli seconds. This is the amount of
     * time the Http client waits before timing out the connection and failing.
     * Default value is 60000 (one minute).
     *
     * @return the client timeout duration for Http Client connections
     */
    public static Integer getHttpClientTimeoutMillis() {
        return httpClientTimeoutMillis;
    }

    /**
     * Set the Http timeout wait time in milli seconds. This is the amount of
     * time the Http client waits before timing out the connection and failing.
     *
     * @param httpClientTimeoutMillis the client timeout wait time for Http Client connections in
     *                                milliseconds.
     */
    public static void setHttpClientTimeoutMillis(Integer httpClientTimeoutMillis) {
        InnotekConfig.httpClientTimeoutMillis = httpClientTimeoutMillis;
    }

    public static Integer getHttpConnectionRequestTimeout() {
        return httpConnectionRequestTimeout;
    }

    public static void setHttpConnectionRequestTimeout(Integer httpConnectionRequestTimeout) {
        InnotekConfig.httpConnectionRequestTimeout = httpConnectionRequestTimeout;
    }
}