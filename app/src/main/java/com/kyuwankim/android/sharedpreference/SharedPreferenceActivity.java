package com.kyuwankim.android.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.kyuwankim.android.sharedpreference.MainActivity.SAHRED_FILE;

public class SharedPreferenceActivity extends AppCompatActivity {

    SeekBar seekBar;
    EditText editText;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    Button btn;
    TextView textView;
    int proGress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        editText = (EditText) findViewById(R.id.editText5);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        textView = (TextView) findViewById(R.id.textView4);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton :

                        break;

                    case R.id.radioButton2 :

                        break;
                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(progress+"");
                proGress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        loadSetting();

    }


    public void saveSetting(View view){
        // 1. Preference 생성하기
        SharedPreferences sharedPref = getSharedPreferences(SAHRED_FILE, Context.MODE_PRIVATE);
        // 2. SharedPreference에 값을 입력하기 위해서는 에디터를 통해서만 가능
        SharedPreferences.Editor editor = sharedPref.edit();

        //editor.putInt( "키", "값");
        editor.putInt("progress",proGress);
        editor.putString("email",    editText.getText().toString());
        editor.putBoolean("radiobutton1", radioButton1.isChecked());
        editor.putBoolean("radiobutton2", radioButton2.isChecked());


        // 3. 입력된 값을 반영
        editor.commit();
    }

    public void loadSetting(){
        SharedPreferences sharedPref = getSharedPreferences(SAHRED_FILE,Context.MODE_PRIVATE);

        // 프로퍼티 가져오기
        String email = sharedPref.getString("email", "");
        boolean radiobutton1 = sharedPref.getBoolean("radiobutton1", false);
        boolean radiobutton2 = sharedPref.getBoolean("radiobutton2", false);
        int progress = sharedPref.getInt("progress",0 );


        // 화면에 세팅
        editText.setText(email);
        radioButton1.setChecked(radiobutton1);
        radioButton2.setChecked(radiobutton2);
        seekBar.setProgress(progress);
        textView.setText(progress+"");

    }
}

