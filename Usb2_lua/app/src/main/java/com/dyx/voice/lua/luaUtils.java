package com.dyx.voice.lua;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class luaUtils {

    public static Map getLuaMap( final Map map, AsyncHttpClient asyncHttpClient){

        asyncHttpClient.get("http://ali.5955555.cn/upgrade/file/", new  TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                LuaState lua = null;
                lua = LuaStateFactory.newLuaState();
                if (lua == null) {

                }
                lua.openLibs();
                lua.LdoString(responseString);

                lua.getGlobal("setText");
                lua.pushJavaObject(map);
                lua.pcall(1, 0, 0);
            }

        });
        return map;
    }


    public static String readAssetsTxt(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "err";
    }


    public static String luaTest(Map map, String key, AsyncHttpClient asyncHttpClient){

        Map luaMap = getLuaMap(map,asyncHttpClient);

        if (luaMap.get(key) == "" &&  luaMap.get(key) == null){
            return "";
        }
        return (String) luaMap.get(key);

    }

}
