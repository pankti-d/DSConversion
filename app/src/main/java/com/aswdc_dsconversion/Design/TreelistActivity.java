package com.aswdc_dsconversion.Design;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc_dsconversion.Adapter.TreeAdapter;
import com.aswdc_dsconversion.Bean.Bean_Tree;
import com.aswdc_dsconversion.DBHelper.Dal_TreeHistory;
import com.aswdc_dsconversion.R;
import com.aswdc_dsconversion.bal.Bal_TreeHistory;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class TreelistActivity extends BaseActivity {
    RecyclerView rcvTree;
    TextView tv_nodata;
    TreeAdapter adapter;
    ArrayList<Bean_Tree> treeslist;
    LinearLayout linearLayout;
    float ALPHA_FULL = 1.0f;

    @Override
    public void initVariables() {
        treeslist = Bal_TreeHistory.getInstance().getString();
        rcvTree = findViewById(R.id.rcvTree);
        linearLayout = findViewById(R.id.treeview);
        tv_nodata = findViewById(R.id.tvnodata3);
    }

    @Override
    public void bindWidgetEvents() {

    }
    void setAdapter(){
        rcvTree.setAdapter(new TreeAdapter(treeslist, this));
        adapter = new TreeAdapter(treeslist, TreelistActivity.this);
        rcvTree.setAdapter(adapter);
        rcvTree.setLayoutManager(new GridLayoutManager(getApplicationContext(),
                1));
        rcvTree.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_treelist);
        super.onCreate(savedInstanceState);

        setTitle("History");
        if(treeslist.isEmpty()) {
            tv_nodata.setVisibility(View.VISIBLE);
//            Toast.makeText(getApplicationContext(),
//                "No Data Found", Toast.LENGTH_SHORT).show();
//            return;
        }
       setAdapter();
        swipe();
    }

    public void removeItem(int position) {
        Dal_TreeHistory.getInstance().deleteTree(treeslist.get(position).getSrNo());
        treeslist.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, treeslist.size());
        Snackbar snackbar = Snackbar
                .make(linearLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);

        snackbar.show();

    }

    public void swipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                final int position = viewHolder.getAdapterPosition();
                Log.d("Dir", swipeDir + "");
                if (swipeDir == 4) {
                    new AlertDialog.Builder(TreelistActivity.this)
                            .setCancelable(false)
                            .setTitle(R.string.alert_delete_title)
                            .setPositiveButton(R.string.alert_yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    removeItem(position);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton(R.string.alert_no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                } else {
                    //adapter.notifyDataSetChanged();
                    Intent intent = new Intent(TreelistActivity.this, AddNodeActivity.class);
                    intent.putExtra("UserTree", treeslist.get(position).getUserTree());
                    startActivity(intent);
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    Paint p = new Paint();
                    Bitmap icon;
                    if (dX > 0) {
                        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_back);
                        p.setARGB(255, 178, 223, 219);
                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                (float) itemView.getBottom(), p);
                        c.drawBitmap(icon,
                                (float) itemView.getLeft() + convertDpToPx(16),
                                (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2,
                                p);
                    } else {
                        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_delete);
                        p.setARGB(200, 255, 102, 102);
                        c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                                (float) itemView.getRight(), (float) itemView.getBottom(), p);
                        c.drawBitmap(icon,
                                (float) itemView.getRight() - convertDpToPx(16) - icon.getWidth(),
                                (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2,
                                p);

                    }
                    final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setTranslationX(dX);

                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rcvTree);
    }

    private int convertDpToPx(int dp) {
        return Math.round(dp * (getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}

