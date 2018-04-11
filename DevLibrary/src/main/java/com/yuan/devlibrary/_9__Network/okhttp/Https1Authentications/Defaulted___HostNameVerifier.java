package com.yuan.devlibrary._9__Network.okhttp.Https1Authentications;

import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

/***********************默认HostNameVerifier实现类*******************/
public class Defaulted___HostNameVerifier implements HostnameVerifier
{
    /***发起Https请求时不去验证Url的主机名和Url的Ip地址***/
    public boolean verify(String s, SSLSession sslSession)
    {
        return true;
    }
}