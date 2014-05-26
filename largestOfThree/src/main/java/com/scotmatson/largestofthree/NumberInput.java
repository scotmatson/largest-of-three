package com.scotmatson.largestofthree;
/* TODO
*   Temporarily disable all button while active
*   Create a slot machine style randomize display
*   Incorporate further OOP concepts, too much work is still going on in the Main Thread
*   Further modularize code structure to enforce 'DRY' principals
* */

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NumberInput extends Activity implements View.OnClickListener, TextWatcher {

    EditText etNum01;
    EditText etNum02;
    EditText etNum03;

    Button bMax;
    Button bRand;
    Button bClear;

    MediaPlayer mpChime;
    MediaPlayer mpRand;
    MediaPlayer mpClear;
    MediaPlayer mpErr;

    int[] userInput = new int[3];
    int largestInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_input);
        initialize();
    }

    public void initialize() {
        etNum01 = (EditText) findViewById(R.id.etNumber01);
        etNum02 = (EditText) findViewById(R.id.etNumber02);
        etNum03 = (EditText) findViewById(R.id.etNumber03);

        etNum01.addTextChangedListener(this);
        etNum02.addTextChangedListener(this);
        etNum03.addTextChangedListener(this);

        bMax = (Button) findViewById(R.id.bMaxValue);
        bRand = (Button) findViewById(R.id.bRandomize);
        bClear = (Button) findViewById(R.id.bClearValues);

        bMax.setOnClickListener(this);
        bRand.setOnClickListener(this);
        bClear.setOnClickListener(this);

        mpChime = MediaPlayer.create(this, R.raw.chime);
        mpRand = MediaPlayer.create(this, R.raw.randomize);
        mpClear = MediaPlayer.create(this, R.raw.clear);
        mpErr = MediaPlayer.create(this, R.raw.error);


    }

    public int editTextToInt(EditText editText) {
        String stringConversion = editText.getText().toString();
        return Integer.parseInt(stringConversion);
    }

    public void etDisable() {
        etNum01.setEnabled(false);
        etNum02.setEnabled(false);
        etNum03.setEnabled(false);

        etNum01.setFocusable(false);
        etNum02.setFocusable(false);
        etNum03.setFocusable(false);
    }

    public void etEnable() {
        etNum01.setEnabled(true);
        etNum02.setEnabled(true);
        etNum03.setEnabled(true);

        etNum01.setFocusableInTouchMode(true);
        etNum02.setFocusableInTouchMode(true);
        etNum03.setFocusableInTouchMode(true);

        etNum01.setFocusable(true);
        etNum02.setFocusable(true);
        etNum03.setFocusable(true);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bMaxValue:
                NumberHelper numberHelper = new NumberHelper(userInput);
                largestInt = numberHelper.getMaxNum();
                Intent outputIntent = new Intent(NumberInput.this, NumberOutput.class);
                Bundle bundle = new Bundle();
                bundle.putInt("largestInt", largestInt);
                outputIntent.putExtras(bundle);

                if(etNum01.getText().length() == 1 && etNum02.getText().length() == 1 && etNum03.getText().length() == 1) {
                    mpChime.start();

                    try {
                        Thread.sleep(125);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(outputIntent);
                }
                else {
                    mpErr.start();
                    Toast badInputType = Toast.makeText(this, "You must enter 3 numbers!", Toast.LENGTH_SHORT);
                    badInputType.show();
                }
                break;

            case R.id.bRandomize:
                    mpRand.start();
                    etNum01.setText(String.valueOf(NumberHelper.randomizer()));
                    etNum02.setText(String.valueOf(NumberHelper.randomizer()));
                    etNum03.setText(String.valueOf(NumberHelper.randomizer()));
                break;

            case R.id.bClearValues:
                mpClear.start();

                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }

                etEnable();
                etNum01.setText("");
                etNum02.setText("");
                etNum03.setText("");
                etNum01.requestFocus();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(etNum01.getText().length() == 1) {
            etNum02.requestFocus();
        }
        if(etNum02.getText().length() == 1) {
            etNum03.requestFocus();
        }
        if(etNum03.getText().length() == 1) {
            if(etNum01.getText().length() == 1 ) {
                etNum02.requestFocus();
            }
            else {
                etNum01.requestFocus();
            }
        }
        if(etNum01.getText().length() == 1 && etNum02.getText().length() == 1 && etNum03.getText().length() == 1) {
            etDisable();
            userInput[0] = editTextToInt(etNum01);
            userInput[1] = editTextToInt(etNum02);
            userInput[2] = editTextToInt(etNum03);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}