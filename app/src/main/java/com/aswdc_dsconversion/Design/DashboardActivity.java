package com.aswdc_dsconversion.Design;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;

import com.aswdc_dsconversion.R;
import com.aswdc_dsconversion.Utility.Constants;
import com.google.android.material.snackbar.Snackbar;

public class DashboardActivity extends BaseActivity {

    Button db_btn_StringConvert, db_btn_BinaryTree, db_btn_PostfixEvaluation, btn_jsDemo, btn_Example;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
        // getMenuInflater().inflate(R.menu.his, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.check_for_update) {
            Intent updateintent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id="+getPackageName()));
            startActivity(updateintent);
        }
        else if (id == R.id.feedback) {
            Intent intent=new Intent(DashboardActivity.this, FeedbackActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.share) {
            Intent share = new Intent();
            share.setAction("android.intent.action.SEND");
            share.setType("text/plain");
            share.putExtra("android.intent.extra.TEXT", Constants.AppPlayStoreLink );
            startActivity(share);
        }
        else if (id == R.id.other_apps) {
            Intent moreappsintent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:Darshan+Institute+of+Engineering+%26+Technology"));
            startActivity(moreappsintent);
        }
        else if (id == R.id.about_us) {
            Intent intent=new Intent(DashboardActivity.this, DeveloperActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initVariables() {
        db_btn_StringConvert = findViewById(R.id.btnStringConversion);
        db_btn_BinaryTree = findViewById(R.id.btnBinaryTree);
        db_btn_PostfixEvaluation = findViewById(R.id.btnPostfixEvaluation);
        btn_Example = findViewById(R.id.btnExamples);
    }

    @Override
    public void bindWidgetEvents() {
        db_btn_StringConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ConversionActivity.class);
                startActivity(intent);
            }
        });

        db_btn_BinaryTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AddNodeActivity.class);
                startActivity(intent);
            }
        });

        db_btn_PostfixEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, EvaluatePostfixActivity.class);
                startActivity(intent);
            }
        });


        btn_Example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ExampleRecyclerActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dashboard);
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(findViewById(android.R.id.content), "Press once again to exit", Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}

