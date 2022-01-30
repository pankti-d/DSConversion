package com.aswdc_dsconversion.Design;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.aswdc_dsconversion.BuildConfig;
import com.aswdc_dsconversion.R;
import com.aswdc_dsconversion.Utility.Constants;

import java.util.Calendar;


public class DeveloperActivity extends BaseActivity {

    TextView icmail, icweb, icshare, icphone, icapp, icrate, iclike, icupdate, iccopy, tvPrivacy;
    TextView appinfo;
    Toolbar tb;
    LinearLayout email, web, call, share, moreapps, rate, likefb, update;
    WebView wvdetail;
    Typeface tf;
    ImageView darshan,aswdc;

    @Override
    public void initVariables() {
        tvPrivacy = findViewById(R.id.dev_tv_privacy);
        icmail = findViewById(R.id.dev_ic_mail);
        icweb = findViewById(R.id.dev_ic_web);
        icshare = findViewById(R.id.dev_ic_share);
        icphone = findViewById(R.id.dev_ic_phone);
        icapp = findViewById(R.id.dev_ic_app);
        icrate = findViewById(R.id.dev_ic_rate);
        iclike = findViewById(R.id.dev_ic_like);
        icupdate = findViewById(R.id.dev_ic_update);
        appinfo = findViewById(R.id.dev_tv_appinfo);
        iccopy = findViewById(R.id.dev_tv_copy);
        call = findViewById(R.id.dev_call);
        darshan=findViewById(R.id.darshan);
        aswdc=findViewById(R.id.aswdc);


        tf = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        icmail.setTypeface(tf);
        icweb.setTypeface(tf);
        icshare.setTypeface(tf);
        icapp.setTypeface(tf);
        icrate.setTypeface(tf);
        icphone.setTypeface(tf);
        iclike.setTypeface(tf);
        icupdate.setTypeface(tf);
        appinfo.setTypeface(tf);
        iccopy.setTypeface(tf);

        email = findViewById(R.id.dev_email);
        web = findViewById(R.id.dev_web);
        share = findViewById(R.id.dev_share);
        moreapps = findViewById(R.id.dev_more_apps);
        rate = findViewById(R.id.dev_rate);
        likefb = findViewById(R.id.dev_like_fb);
        update = findViewById(R.id.dev_update);
        wvdetail = findViewById(R.id.developer_wv_detail);


        wvdetail.loadData("<html><body align=\"justify\" style=\"font-size:15px;color:#747474\">ASWDC is Application, Software and Website Development Center @ Darshan Engineering College run by Students and Staff of Computer Engineering Department.<br><br> Sole purpose of ASWDC is to bridge gap between university curriculum &amp; industry demands. Students learn cutting edge technologies, develop real world application &amp; experiences professional environment @ ASWDC under guidance of industry experts &amp; faculty members", "text/html", "utf-8");

        appinfo.setText(getResources().getString(R.string.app_name) + " (v" + BuildConfig.VERSION_NAME + ")");
    }

    @Override
    public void bindWidgetEvents() {
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailintent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", Constants.ASWDCEmailAddress, null));
                emailintent.putExtra(Intent.EXTRA_SUBJECT, "Contact from " + getString(R.string.app_name));
                startActivity(Intent.createChooser(emailintent, "Send Email to ASWDC"));
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+" + Constants.AdminMobileNo));
                startActivity(intent);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.darshan.ac.in"));
                startActivity(webintent);
            }
        });
        darshan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.darshan.ac.in"));
                startActivity(webintent);

            }
        });
        aswdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://aswdc.in"));
                startActivity(webintent);

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, Constants.AppPlayStoreLink);
                startActivity(share);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent rateintent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName()));
                    startActivity(rateintent);
                } catch (ActivityNotFoundException e) {
                    Intent moreappsintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                    startActivity(moreappsintent);
                }
            }
        });

        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent moreappsintent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Darshan+Institute+of+Engineering+%26+Technology"));
                    startActivity(moreappsintent);
                } catch (ActivityNotFoundException e) {
                    Intent moreappsintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                    startActivity(moreappsintent);
                }
            }
        });

        likefb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/DarshanInstitute.Official"));
                startActivity(fbintent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent updateintent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName()));
                    startActivity(updateintent);
                } catch (ActivityNotFoundException e) {
                    Intent moreappsintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                    startActivity(moreappsintent);
                }

            }
        });
        tvPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.darshan.ac.in/DIET/ASWDC-Mobile-Apps/Privacy-Policy-General"));
                startActivity(webintent);
            }
        });
        String strInst = getString(R.string.inst_name).toString();
        iccopy.setText("\uf1f9 " + Calendar.getInstance().get(Calendar.YEAR) + " " + strInst);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_developer);
        super.onCreate(savedInstanceState);

        setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
        @Override
        protected void onDestroy () {
            super.onDestroy();
            wvdetail.destroy();
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home:
                    // NavUtils.navigateUpFromSameTask(this);
                    finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }


}

