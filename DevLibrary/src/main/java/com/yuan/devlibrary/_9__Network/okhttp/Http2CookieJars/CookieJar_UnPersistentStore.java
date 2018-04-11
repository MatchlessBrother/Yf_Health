package com.yuan.devlibrary._9__Network.okhttp.Http2CookieJars;

import java.util.Map;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.CookieJar;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/*****************Cookie只存在于运行内存中******************/
/******************App关闭后Cookie自动消失******************/
public class CookieJar_UnPersistentStore implements CookieJar
{
    private Map<String,List<Cookie>> mDataMap;

    public CookieJar_UnPersistentStore()
    {
        this(new ConcurrentHashMap<String,List<Cookie>>());
    }

    public CookieJar_UnPersistentStore(Map<String,List<Cookie>> dataMap)
    {
        mDataMap = dataMap;
    }

    @Override
    /***********直接将所有cookie存储在mDataMap运行缓存中**********/
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
    {
        mDataMap.put(url.host(),cookies);
    }

    @Override
    /***********直接从mDataMap运行缓存中读取所有cookie************/
    public List<Cookie> loadForRequest(HttpUrl url)
    {
        return null != mDataMap.get(url.host()) ? mDataMap.get(url.host()) : new ArrayList<Cookie>();
    }
}