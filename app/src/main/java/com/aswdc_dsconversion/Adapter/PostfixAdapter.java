package com.aswdc_dsconversion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc_dsconversion.Bean.Bean_Postfix;
import com.aswdc_dsconversion.R;

import java.util.ArrayList;

public class PostfixAdapter extends RecyclerView.Adapter<PostfixAdapter.CandidateHolder> {
    ArrayList<Bean_Postfix> bean_postfixes;
    Context context;
    LayoutInflater layoutInflater;
    //DB_EvaluatePostfix db_evaluatePostfix;

    public PostfixAdapter(ArrayList<Bean_Postfix> bean_postfixes,
                       Context context) {
        this.bean_postfixes = bean_postfixes;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //db_evaluatePostfix = new DB_EvaluatePostfix(context);
    }


    @NonNull
    @Override
    public PostfixAdapter.CandidateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.postfixlist_item, null);
        return new PostfixAdapter.CandidateHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull PostfixAdapter.CandidateHolder viewHolder, final int position) {

        viewHolder.tvUserExp.setText("User Expression: "+bean_postfixes.get(position).getUserExp());
        viewHolder.tvAnswer.setText("Answer: "+bean_postfixes.get(position).getAnswer());


    }

    @Override
    public int getItemCount() {
        return bean_postfixes.size();

    }

    class CandidateHolder extends RecyclerView.ViewHolder {
        TextView tvUserExp, tvAnswer;
        LinearLayout linearLayout;

        public CandidateHolder(View v) {
            super(v);
            tvUserExp= v.findViewById(R.id.tvUserExp);
            tvAnswer = v.findViewById(R.id.tvAnswer);
            linearLayout = v.findViewById(R.id.linearlay4);


        }

    }
}



