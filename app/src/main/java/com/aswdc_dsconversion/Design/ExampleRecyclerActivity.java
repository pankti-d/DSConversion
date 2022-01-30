package com.aswdc_dsconversion.Design;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc_dsconversion.Adapter.RecyclerViewAdapter;
import com.aswdc_dsconversion.Bean.Bean_Examples;
import com.aswdc_dsconversion.R;
import com.aswdc_dsconversion.bal.Bal_Examples;

import java.util.ArrayList;

public class ExampleRecyclerActivity extends BaseActivity {
    RecyclerView recyclerView;
    ArrayList<Bean_Examples> exampleList;
    RecyclerViewAdapter adapter;

    @Override
    public void initVariables() {
        recyclerView = findViewById(R.id.rcvExamples);
        exampleList = Bal_Examples.getInstance().getExamples();
    }

    @Override
    public void bindWidgetEvents() {

    }
    void setAdapter(){
        adapter = new RecyclerViewAdapter(exampleList, ExampleRecyclerActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),
                1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_examplelist);
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Examples");
        setAdapter();

    }
}
