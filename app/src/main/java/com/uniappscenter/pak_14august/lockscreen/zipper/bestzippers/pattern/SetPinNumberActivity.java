package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;


public class SetPinNumberActivity extends AppCompatActivity {

    private EditText password1, password2, password3, password4;
    private TextView tv_passcode;
    private int counter = 0;
    private int value;
    private int firstTry = 0;
    private String comparePinResult;
    private String msgTxt;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twelve_key_entry);

        sharedPreferences = getSharedPreferences(ConstantData.SHARE_PREFERENCE, Context.MODE_PRIVATE);

        tv_passcode = findViewById(R.id.tv_passcode);
        password1 = findViewById(R.id.password01);
        password2 = findViewById(R.id.password02);
        password3 = findViewById(R.id.password03);
        password4 = findViewById(R.id.password04);

        //loadInterstitialAd();

        twelve_button();
    }


    private void twelve_button() {

        TextView onekey, twokey, threekey, fourkey, fivekey, sixkey, sevenkey, eightkey, ninekey, zerokey, delete, passcode;

        onekey = findViewById(R.id.one);
        twokey = findViewById(R.id.two);
        threekey = findViewById(R.id.three);
        fourkey = findViewById(R.id.four);
        fivekey = findViewById(R.id.five);
        sixkey = findViewById(R.id.six);
        sevenkey = findViewById(R.id.seven);
        eightkey = findViewById(R.id.eight);
        ninekey = findViewById(R.id.nine);
        zerokey = findViewById(R.id.zero);
        delete = findViewById(R.id.cancel);
        passcode = findViewById(R.id.tv_passcode);

        Typeface Pinfont = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue-Thin.otf");

        //onekey.setTypeface(Pinfont);
        onekey.setTextSize(35);
        onekey.setTypeface(Pinfont);

        twokey.setTypeface(Pinfont);
        twokey.setTextSize(35);
        threekey.setTypeface(Pinfont);
        threekey.setTextSize(35);
        fourkey.setTypeface(Pinfont);
        fourkey.setTextSize(35);
        fivekey.setTypeface(Pinfont);
        fivekey.setTextSize(35);
        sixkey.setTypeface(Pinfont);
        sixkey.setTextSize(35);
        sevenkey.setTypeface(Pinfont);
        sevenkey.setTextSize(35);
        eightkey.setTypeface(Pinfont);
        eightkey.setTextSize(35);
        ninekey.setTypeface(Pinfont);
        ninekey.setTextSize(35);
        zerokey.setTypeface(Pinfont);
        zerokey.setTextSize(35);
        delete.setTypeface(Pinfont);
        delete.setTextSize(20);
        passcode.setTypeface(Pinfont);
        passcode.setTextSize(20);


        onekey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 1;
            ++counter;
            counter(value);
        });
        twokey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 2;
            ++counter;
            counter(value);
        });
        threekey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 3;
            ++counter;
            counter(value);
        });
        fourkey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 4;
            ++counter;
            counter(value);
        });
        fivekey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 5;
            ++counter;
            counter(value);
        });
        sixkey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 6;
            ++counter;
            counter(value);
        });
        sevenkey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 7;
            ++counter;
            counter(value);
        });
        eightkey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 8;
            ++counter;
            counter(value);
        });
        ninekey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 9;
            ++counter;
            counter(value);
        });
        zerokey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 0;
            ++counter;
            counter(value);
        });

        delete.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = -1;
            ++counter;
            counter(value);
        });

    }

    @SuppressLint("SetTextI18n")
    private void counter(int val) {

        if (val == -2) {
            password1.setText("");
            password2.setText("");
            password3.setText("");
            password4.setText("");

            counter = 0;

        }

        //Delete btn
        if (val == -1) {
            --counter;
            switch (counter) {
                case 1:
                    password1.setText("");
                    password1.setBackgroundResource(R.drawable.pin_step_off);
                    counter--;
                    break;
                case 2:
                    password2.setText("");
                    password2.setBackgroundResource(R.drawable.pin_step_off);
                    counter--;
                    break;
                case 3:
                    password3.setText("");
                    password3.setBackgroundResource(R.drawable.pin_step_off);
                    counter--;
                    break;
                case 4:
                    password4.setText("");
                    password4.setBackgroundResource(R.drawable.pin_step_off);
                    counter--;
                    break;

                default:
                    break;
            }
        } else {

            switch (counter) {

                case 1: {
                    password1.setText("" + val);
                    password1.setBackgroundResource(R.drawable.pin_step_on);
                    break;
                }
                case 2: {
                    password2.setText("" + val);
                    password2.setBackgroundResource(R.drawable.pin_step_on);
                    break;
                }
                case 3: {
                    password3.setText("" + val);
                    password3.setBackgroundResource(R.drawable.pin_step_on);
                    break;
                }
                case 4: {
                    password4.setText("" + val);
                    password4.setBackgroundResource(R.drawable.pin_step_on);

                    String result = password1.getText().toString()
                            + password2.getText().toString()
                            + password3.getText().toString()
                            + password4.getText().toString();

                    //Call from setting screen

                    //First Try
                    if (firstTry == 0) {
                        comparePinResult = result;

                        msgTxt = "Enter Passcode Again";
                        clearPinPattern();
                        firstTry++;

                    }
                    ////  Second Try
                    else if (firstTry == 1) {
                        if (comparePinResult.equals(result)) {


                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("password", result);
                            editor.apply();

                            setResult(Activity.RESULT_OK);
                            finish();

                        }

                        //Not match pin on second try
                        else {
                            msgTxt = "Wrong passcode try again";
                            clearPinPattern();
                            firstTry = 0;
                        }
                    }
                    break;
                }
            }
        }
    }


    private void clearPinPattern() {

        if (msgTxt != null)
            tv_passcode.setText(msgTxt);

        password1.setText("");
        password1.setBackgroundResource(R.drawable.pin_step_off);
        password2.setText("");
        password2.setBackgroundResource(R.drawable.pin_step_off);
        password3.setText("");
        password3.setBackgroundResource(R.drawable.pin_step_off);
        password4.setText("");
        password4.setBackgroundResource(R.drawable.pin_step_off);

        counter = 0;
    }

    // Runnable mRunnable = this::clearPinPattern;

}

