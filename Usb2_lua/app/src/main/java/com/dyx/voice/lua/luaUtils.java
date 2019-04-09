package com.dyx.voice.lua;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class luaUtils {

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
}
