package com.aswdc_dsconversion.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc_dsconversion.Bean.Bean_Examples;
import com.aswdc_dsconversion.Design.ExampleDisplayActivity;
import com.aswdc_dsconversion.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CandidateHolder> {

    ArrayList<Bean_Examples> bean_examples;
    Activity context;
    LayoutInflater layoutInflater;



    public RecyclerViewAdapter(ArrayList<Bean_Examples> bean_examples,Activity context){
        this.bean_examples=bean_examples;
        this.context=context;
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);



    }

    @NonNull
    @Override
    public CandidateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =layoutInflater.inflate(R.layout.activity_eglist_item,null);
        return new CandidateHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull CandidateHolder viewHolder, final int position) {

        viewHolder.tvExample.setText(bean_examples.get(position).getExample());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                Bundle bundle = new Bundle();
                bundle.putString("html", bean_examples.get(position).getHtml());

                Intent intent = new Intent(context, ExampleDisplayActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });


    }
    @Override
    public int getItemCount() {
        return bean_examples.size();

    }

    class CandidateHolder extends RecyclerView.ViewHolder{
        TextView tvExample;
        //ImageView img;
        LinearLayout linearLayout;
        // ImageView ivImage;
        public CandidateHolder(View v){
            super(v);
            //img = v.findViewById(R.id.imgarrow);
            tvExample=v.findViewById(R.id.tvExample);
            linearLayout= v.findViewById(R.id.linearlay1);

            //ivImage =v.findViewById(R.id.iv_display_name_logo);

        }
    }

    /*public void updatelists(ArrayList<Bean_Examples> newList)
    {
        bean_examples = new ArrayList<>();
        bean_examples.addAll(newList);
        notifyDataSetChanged();
    }*/
}