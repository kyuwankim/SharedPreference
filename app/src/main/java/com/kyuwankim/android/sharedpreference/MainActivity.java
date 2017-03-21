package com.kyuwankim.android.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    String internalStoragePath;
    final String propertyFile = "test.properties";
    PropertyUtil propertyUtil;
    EditText editname;
    Switch switchshuffle;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internalStoragePath = getFilesDir().getAbsolutePath();
        editname = (EditText) findViewById(R.id.editText);
        switchshuffle = (Switch) findViewById(R.id.switch1);
        layout = (RelativeLayout) findViewById(R.id.layout2);
        propertyUtil = PropertyUtil.getInstance(this);


        if ("false".equals(propertyUtil.getProperty("firstOpen"))) {
            layout.setVisibility(View.GONE);
        }

        loadSetting();
    }


    public void closeHelp(View view) {
        layout.setVisibility(View.GONE);
        propertyUtil.saveProperty("firstOpen", "false");

    }

    public void saveSetting(View view) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", editname.getText().toString());
        editor.putBoolean("shuffle", switchshuffle.isChecked());
        editor.commit();

    }

    public void loadSetting() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        String email = sharedPref.getString("email", null);
        boolean shuffle = sharedPref.getBoolean("shuffle", false);

        editname.setText(email);
        switchshuffle.setChecked(shuffle);
    }


}
