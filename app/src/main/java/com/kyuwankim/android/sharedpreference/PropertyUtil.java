package com.kyuwankim.android.sharedpreference;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by kimkyuwan on 2017. 3. 21..
 */

public class PropertyUtil {
    private String PROP_FILE = "sp.properties";
    private String internalStorage;
    private static Context context;
    private static PropertyUtil instance = null;

    // 생성자가 호출될때 internalStorage를 세팅해줘야 함
    private PropertyUtil() {
        internalStorage = context.getFilesDir().getAbsolutePath();
    }

    public static PropertyUtil getInstance(Context ctx) {
        context = ctx;
        if (instance == null) {
            instance = new PropertyUtil();
        }
        return instance;
    }

    public void saveProperty(String key, String value) {
        Properties prop = new Properties();
        prop.put(key, value);

        try {

            FileOutputStream fos = new FileOutputStream(internalStorage + "/" + PROP_FILE);

            prop.store(fos, "comment");

            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getProperty(String key) {
        String value = "";

        Properties prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(internalStorage + "/" + PROP_FILE);
            prop.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        value = prop.getProperty(key);

        return value;

    }
}
