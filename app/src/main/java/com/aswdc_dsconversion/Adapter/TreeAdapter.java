package com.aswdc_dsconversion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc_dsconversion.Bean.Bean_Tree;
import com.aswdc_dsconversion.R;

import java.util.ArrayList;

public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.CandidateHolder> {
    ArrayList<Bean_Tree> bean_trees;
    Context context;
    LayoutInflater layoutInflater;

    public TreeAdapter(ArrayList<Bean_Tree> bean_trees,
                            Context context) {
        this.bean_trees = bean_trees;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //db_treeHistory = new DB_TreeHistory(context);
    }


    @NonNull
    @Override
    public TreeAdapter.CandidateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.treelist_item, null);
        return new TreeAdapter.CandidateHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull TreeAdapter.CandidateHolder viewHolder, final int position) {

        viewHolder.tvUserTree.setText("User Nodes: "+bean_trees.get(position).getUserTree());
        viewHolder.tvinorder.setText("Inoder: "+bean_trees.get(position).getInOrder());


    }

    @Override
    public int getItemCount() {
        return bean_trees.size();

    }

    class CandidateHolder extends RecyclerView.ViewHolder {
        TextView tvUserTree, tvinorder;
        LinearLayout linearLayout;

        public CandidateHolder(View v) {
            super(v);
            tvUserTree= v.findViewById(R.id.tvUserTree);
            tvinorder = v.findViewById(R.id.tvInorder);
            linearLayout = v.findViewById(R.id.linearlay3);


        }

    }
}


