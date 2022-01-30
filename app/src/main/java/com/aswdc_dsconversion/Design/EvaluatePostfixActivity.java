package com.aswdc_dsconversion.Design;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;

import com.aswdc_dsconversion.DBHelper.Dal_EvaluatePostfix;
import com.aswdc_dsconversion.Helper.EvaluatePostfix;
import com.aswdc_dsconversion.R;

public class EvaluatePostfixActivity extends BaseActivity {
    Button btn_evaluate, btn_clear;
    EditText et_postfixString;
    TextView tv_evaluateResult, tvNote;
    String inputString;
    EvaluatePostfix ev;
    CardView cvAnswer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history, menu);
        // getMenuInflater().inflate(R.menu.his, menu);
        return true;
    }

    @Override
    public void initVariables() {
        ev = new EvaluatePostfix();
        cvAnswer = findViewById(R.id.cvAnswer2);
        btn_evaluate = findViewById(R.id.ac_btn_evaluate);
        btn_clear = findViewById(R.id.ac_btn_clear);
        et_postfixString = findViewById(R.id.ac_et_postfixString);
        tv_evaluateResult = findViewById(R.id.ac_tv_evaluateResult);
        tvNote = findViewById(R.id.tvNote);
    }

    @Override
    public void bindWidgetEvents() {
        btn_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputString = et_postfixString.getText().toString();
                if(inputString.equals("")){Toast.makeText(getApplicationContext(),
                        "Please Enter an Expression", Toast.LENGTH_SHORT).show();
                    return;}
                if(ev.evaluatePostfix(inputString.trim())==-1){
                    Toast.makeText(getApplicationContext(),
                            "Please Enter a Valid Postfix With Space", Toast.LENGTH_SHORT).show();
                    return;
                }
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                tv_evaluateResult.setText(ev.evaluatePostfix(inputString.trim())+"");
                cvAnswer.setVisibility(View.VISIBLE);
                insertContent();
            }
        });
        et_postfixString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputString = et_postfixString.getText().toString();
                if(inputString.equals("")){
                    et_postfixString.setText("");
                    cvAnswer.setVisibility(View.GONE);
                    tv_evaluateResult.setText("");
                }
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                et_postfixString.setText("");
                cvAnswer.setVisibility(View.GONE);
                tv_evaluateResult.setText("");
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_evaluatepostfix);
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Evaluate Postfix");


        if(getIntent().hasExtra("UserExp")){
            inputString = getIntent().getStringExtra("UserExp");
            et_postfixString.setText(inputString);
            tv_evaluateResult.setText(ev.evaluatePostfix(inputString.trim())+"");
            cvAnswer.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Intent intent = new Intent(EvaluatePostfixActivity.this, PostfixlistActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void insertContent() {
        ContentValues cv = new ContentValues();
        cv.put(Dal_EvaluatePostfix._UserExp, et_postfixString.getText().toString());
        cv.put(Dal_EvaluatePostfix._Answer, tv_evaluateResult.getText().toString());
        Dal_EvaluatePostfix.getInstance().insertContent(cv);
    }
}