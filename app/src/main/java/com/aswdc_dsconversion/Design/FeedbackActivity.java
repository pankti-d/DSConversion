package com.aswdc_dsconversion.Design;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.aswdc_dsconversion.Bean.Model_ApiResponse;
import com.aswdc_dsconversion.BuildConfig;
import com.aswdc_dsconversion.R;
import com.aswdc_dsconversion.api.ApiClient;
import com.aswdc_dsconversion.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends BaseActivity {

    TextView tvName;
    TextView tvMobile;
    TextView tvRemark;
    TextView tvEmail;
    Button btnSend;
    Button btnClear;
    ProgressDialog mProgressDialog;
    ApiInterface apiService;
    String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\'(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\')@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    public void initVariables() {
        tvName = (TextView) findViewById(R.id.feedback_tv_name);
        tvMobile = (TextView) findViewById(R.id.feedback_tv_mobile);
        tvRemark = (TextView) findViewById(R.id.feedback_tv_remark);
        tvEmail = (TextView) findViewById(R.id.feedback_tv_email);
        btnSend = (Button) findViewById(R.id.feedback_btn_send);
        btnClear = (Button) findViewById(R.id.feedback_btn_clear);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Sending...");
    }

    @Override
    public void bindWidgetEvents() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {

                    if (CheckNetwork.isOnline(FeedbackActivity.this))
                        sendFeedBack();
                    else {
                        new AlertDialog.Builder(FeedbackActivity.this)
                                .setMessage(R.string.error_message_internet_connection)
                                .setPositiveButton("Ok", null)
                                .show();
                    }

                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_feedback);
        super.onCreate(savedInstanceState);
        setTitle("Feedback");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



    }

    boolean validation() {

        boolean flage = true;
        if (tvRemark.getText().toString().length() <= 0) {
            tvRemark.setError("Enter Remark");
            tvRemark.requestFocus();
            flage = false;
        } else if (tvRemark.getText().toString().equalsIgnoreCase(".")) {
            tvRemark.setError("Invalid Input");
            tvRemark.requestFocus();
            flage = false;
        }
//        if (tvEmail.getText().toString().length() <= 0) {
//            tvEmail.setError("Enter Email");
//            tvEmail.requestFocus();
//            flage = false;
//        } else
        if (tvEmail.getText().toString().equalsIgnoreCase(".")) {
            tvEmail.setError("Invalid Input");
            tvEmail.requestFocus();
            flage = false;
        } else if (tvEmail.getText().toString().length() != 0) {
            if ((!tvEmail.getText().toString().matches(emailPattern))) {
                tvEmail.setError("Enter Valid Email Id");
                tvEmail.requestFocus();
                flage = false;
            }
        }
//        if (tvMobile.getText().toString().length() <= 0) {
//            tvMobile.setError("Enter Mobile Number");
//            tvMobile.requestFocus();
//            flage = false;
//        } else
        if (tvMobile.getText().toString().equalsIgnoreCase(".")) {
            tvMobile.setError("Invalid Input");
            tvMobile.requestFocus();
            flage = false;
        } else if (tvMobile.getText().toString().length() < 12 && tvMobile.getText().toString().length() > 12) {
            tvMobile.setError("Enter Valid Mobile Number");
            tvMobile.requestFocus();
            flage = false;
        }
        if (tvName.getText().toString().length() <= 0) {
            tvName.setError("Enter Name");
            tvName.requestFocus();
            flage = false;
        } else if (tvName.getText().toString().equalsIgnoreCase(".")) {
            tvName.setError("Invalid Input");
            tvName.requestFocus();
            flage = false;
        }
        return flage;
    }

    void clear() {
        tvName.setText("");
        tvName.setError(null);
        tvEmail.setText("");
        tvEmail.setError(null);
        tvMobile.setText("");
        tvMobile.setError(null);
        tvRemark.setText("");
        tvRemark.setError(null);
    }

    public void sendFeedBack() {
        mProgressDialog.show();
        apiService = ApiClient.getClientContact().create(ApiInterface.class);
        Call<Model_ApiResponse> call = apiService.insertFeedback(BuildConfig.app_key_feedback, "Banglore Metro", BuildConfig.VERSION_CODE + "", "Android", tvName.getText().toString() + "", tvMobile.getText().toString() + "", tvEmail.getText().toString() + "", tvRemark.getText().toString() + "", "");
        call.enqueue(new Callback<Model_ApiResponse>() {
            @Override
            public void onResponse(Call<Model_ApiResponse> call, Response<Model_ApiResponse> response) {

                if (response.body().getIsResult() == 1)
                    Toast.makeText(FeedbackActivity.this, "Your message has been send successfully.", Toast.LENGTH_LONG).show();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(Call<Model_ApiResponse> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }

        });
        btnClear.performClick();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean handleReturn = super.dispatchTouchEvent(ev);

        View view = getCurrentFocus();

        int x = (int) ev.getX();
        int y = (int) ev.getY();

        if (view instanceof EditText) {
            View innerView = getCurrentFocus();

            if (ev.getAction() == MotionEvent.ACTION_UP &&
                    !getLocationOnScreen(innerView).contains(x, y)) {

                InputMethodManager input = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(getWindow().getCurrentFocus()
                        .getWindowToken(), 0);
            }
        }

        return handleReturn;
    }

    protected Rect getLocationOnScreen(View mEditText) {
        Rect mRect = new Rect();
        int[] location = new int[2];

        mEditText.getLocationOnScreen(location);

        mRect.left = location[0];
        mRect.top = location[1];
        mRect.right = location[0] + mEditText.getWidth();
        mRect.bottom = location[1] + mEditText.getHeight();

        return mRect;
    }
}
