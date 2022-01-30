package com.aswdc_dsconversion.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc_dsconversion.Bean.Bean_String;
import com.aswdc_dsconversion.R;

import java.util.ArrayList;

public class StinglistAdapter extends RecyclerView.Adapter<StinglistAdapter.CandidateHolder> {

    ArrayList<Bean_String> bean_strings;
    Activity context;
    LayoutInflater layoutInflater;
    //DB_StringHistory db_stringHistory;
    StinglistAdapter adapter;


    public StinglistAdapter(ArrayList<Bean_String> bean_strings, Activity context) {
        this.bean_strings = bean_strings;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //db_stringHistory = new DB_StringHistory(context);


    }

    @NonNull
    @Override
    public CandidateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.stringlist_item, null);
        return new CandidateHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull CandidateHolder viewHolder, final int position) {

        viewHolder.tvConversionType.setText(bean_strings.get(position).getConversionType());
        viewHolder.tvUserString.setText("User String: " + bean_strings.get(position).getUserString());
        viewHolder.tvanswer.setText("Result: " + bean_strings.get(position).getAnswer());


    }

    @Override
    public int getItemCount() {
        return bean_strings.size();

    }

    class CandidateHolder extends RecyclerView.ViewHolder {
        TextView tvConversionType, tvUserString, tvanswer;
        LinearLayout linearLayout;

        public CandidateHolder(View v) {
            super(v);
            tvConversionType = v.findViewById(R.id.tvConversionType);
            tvUserString = v.findViewById(R.id.tvUserString);
            tvanswer = v.findViewById(R.id.tvAnswer);
            linearLayout = v.findViewById(R.id.linearlay2);


        }

    }
}

