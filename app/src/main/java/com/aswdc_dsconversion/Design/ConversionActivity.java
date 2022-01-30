package com.aswdc_dsconversion.Design;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;

import com.aswdc_dsconversion.DBHelper.Dal_StringHistory;
import com.aswdc_dsconversion.Helper.InfixToPostfix;
import com.aswdc_dsconversion.Helper.PostfixToInfix;
import com.aswdc_dsconversion.Helper.PostfixToPrefix;
import com.aswdc_dsconversion.Helper.PrefixToInfix;
import com.aswdc_dsconversion.Helper.PrefixToPostfix;
import com.aswdc_dsconversion.R;

public class ConversionActivity extends BaseActivity {
    private static final String TAG = "ConversionActivity";

    Button btn_Convert, btn_clear;
    EditText et_String;
    TextView tv_result;
    Spinner spConversion;
    String[] ConvertOption;
    String InputString;
    CardView cvAnswer;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history, menu);
        // getMenuInflater().inflate(R.menu.his, menu);
        return true;
    }


    @Override
    public void initVariables() {
        btn_Convert = findViewById(R.id.ac_btn_convert);
        btn_clear = findViewById(R.id.ac_btn_clear);
        et_String = findViewById(R.id.ac_et_String);
        spConversion = findViewById(R.id.ac_spProfile);
        tv_result = findViewById(R.id.ac_tv_result);
        cvAnswer = findViewById(R.id.cvAnswer);
        ConvertOption = getResources().getStringArray(R.array.Conversion);
    }

    @Override
    public void bindWidgetEvents() {
        btn_Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                String selectedOption = spConversion.getSelectedItem().toString();
                String inputString = et_String.getText().toString();
                Log.d(TAG, "onClick: working listener: " + selectedOption);
                if (selectedOption.equals("Select a conversion")) {
                    Toast.makeText(getApplicationContext(),
                            "Please select a Conversion", Toast.LENGTH_SHORT).show();

                    return;

                } else if (inputString.matches("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please Enter a String", Toast.LENGTH_SHORT).show();

                    return;
                } else if (selectedOption.equals("Infix to Postfix")) {
                    InfixToPostfix inToPost = new InfixToPostfix();
                    String check = inToPost.infixToPostfix(inputString);
                    tv_result.setText(inToPost.infixToPostfix(inputString));
                    cvAnswer.setVisibility(View.VISIBLE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    insertContent();
                } else if (selectedOption.equals("Postfix to Infix")) {
                    PostfixToInfix postToIn = new PostfixToInfix();
                    String check = postToIn.convert(inputString);
                    if (check.equals("Please enter a Valid Postfix")) {
                        Toast.makeText(getApplicationContext(),
                                "Please Enter a Valid Postfix", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tv_result.setText(postToIn.convert(inputString));
                    cvAnswer.setVisibility(View.VISIBLE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    insertContent();
                } else if (selectedOption.equals("Prefix to Infix")) {
                    PrefixToInfix pretoIn = new PrefixToInfix();
                    String check = (pretoIn.prefixToInfix(inputString));
                    if (check.equals("Enter a Valid Prefix")) {
                        Toast.makeText(getApplicationContext(),
                                "Please Enter a Valid Prefix", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tv_result.setText(pretoIn.prefixToInfix(inputString));
                    cvAnswer.setVisibility(View.VISIBLE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    insertContent();

                } else if (selectedOption.equals("Postfix to Prefix")) {
                    PostfixToPrefix postToPre = new PostfixToPrefix();
                    String check = postToPre.postfixToPrefix(inputString);
                    if (check.equals("Please Enter a Valid Postfix")) {
                        Toast.makeText(getApplicationContext(),
                                "Please Enter a Valid Postfix", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tv_result.setText(postToPre.postfixToPrefix(inputString));
                    cvAnswer.setVisibility(View.VISIBLE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    insertContent();
                } else if (selectedOption.equals("Prefix to Postfix")) {
                    PrefixToPostfix preToPost = new PrefixToPostfix();
                    String check = preToPost.prefixToPostfix(inputString);
                    if (check.equals("Please Enter a Valid Prefix")) {
                        Toast.makeText(getApplicationContext(),
                                "Please Enter a Valid Prefix", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tv_result.setText(preToPost.prefixToPostfix(inputString));
                    cvAnswer.setVisibility(View.VISIBLE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    insertContent();

                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spConversion.performClick();
                et_String.setHint("");
                et_String.setText("");
                tv_result.setText("");
                cvAnswer.setVisibility(View.GONE);
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });

    }

    void setAdapter() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, ConvertOption);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spConversion.setAdapter(spinnerArrayAdapter);

        if (getIntent().hasExtra("UserString")) {
            String inputString = getIntent().getStringExtra("UserString");
            et_String.setText(inputString);
            String selectedOption = getIntent().getStringExtra("Type");
            spConversion.setSelection(getIndex(spConversion, selectedOption));
            String answer = getIntent().getStringExtra("Answer");
            tv_result.setText(answer);
            cvAnswer.setVisibility(View.VISIBLE);
        }
        else{
        spConversion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = spConversion.getSelectedItem().toString();
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
//                        InputMethodManager.HIDE_NOT_ALWAYS);
                if (selectedOption.equals("Infix to Postfix")) {
                    et_String.setHint("(a+b)*(c-d)");
                    et_String.setText("");
                    tv_result.setText("");
                    cvAnswer.setVisibility(View.GONE);

                } else if (selectedOption.equals("Postfix to Infix")) {
                    et_String.setHint("ab+cd-*");
                    et_String.setText("");
                    tv_result.setText("");
                    cvAnswer.setVisibility(View.GONE);


                } else if (selectedOption.equals("Prefix to Infix")) {
                    et_String.setHint("*+ab-cd");
                    et_String.setText("");
                    tv_result.setText("");
                    cvAnswer.setVisibility(View.GONE);


                } else if (selectedOption.equals("Postfix to Prefix")) {
                    et_String.setHint("ab+cd-*");
                    et_String.setText("");
                    tv_result.setText("");
                    cvAnswer.setVisibility(View.GONE);


                } else if (selectedOption.equals("Prefix to Postfix")) {
                    et_String.setHint("*+ab-cd");
                    et_String.setText("");
                    tv_result.setText("");
                    cvAnswer.setVisibility(View.GONE);


                } else if (selectedOption.equals("Select a Conversion")) {
                    et_String.setHint("");
                    et_String.setText("");
                    tv_result.setText("");
                    cvAnswer.setVisibility(View.GONE);

                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_conversion);
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("String Conversion");
        setAdapter();

//        if(getIntent().hasExtra("UserString")){
//            String inputString = getIntent().getStringExtra("UserString");
//            et_String.setText("Hello");
////            tv_evaluateResult.setText(ev.evaluatePostfix(inputString.trim())+"");
////            cvAnswer.setVisibility(View.VISIBLE);
//        }
//        if(getIntent().hasExtra("Type")){
//            String selectedOption = getIntent().getStringExtra("Type");
//            spConversion.setSelection(getIndex(spConversion, selectedOption));
//        }

    }

    boolean isValidString() {
        if (TextUtils.isEmpty(InputString)) {
            Toast.makeText(getApplicationContext(),
                    "Enter a String", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void insertContent() {
        ContentValues cv = new ContentValues();
        cv.put(Dal_StringHistory._UserString, et_String.getText().toString());
        cv.put(Dal_StringHistory._Answer, tv_result.getText().toString());
        cv.put(Dal_StringHistory._ConversionType, ConvertOption[spConversion.getSelectedItemPosition()]);
        Dal_StringHistory.getInstance().insertContent(cv);
    }

    public int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Intent intent = new Intent(ConversionActivity.this, StringlistActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
