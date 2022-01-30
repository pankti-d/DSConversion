package com.aswdc_dsconversion.Design;

import android.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.aswdc_dsconversion.Adapter.PostfixAdapter;
import com.aswdc_dsconversion.Bean.Bean_Postfix;
import com.aswdc_dsconversion.DBHelper.Dal_EvaluatePostfix;
import com.aswdc_dsconversion.R;
import com.aswdc_dsconversion.bal.Bal_EvaluatePostfix;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class PostfixlistActivity extends BaseActivity {
    RecyclerView rcvPostfix;
    TextView tv_nodata;
    PostfixAdapter adapter;
    ArrayList<Bean_Postfix> postfixeslist;
    LinearLayout linearLayout;
    float ALPHA_FULL = 1.0f;

    @Override
    public void initVariables() {
        postfixeslist = Bal_EvaluatePostfix.getInstance().getPostfix();
        rcvPostfix = findViewById(R.id.rcvPostfix);
        linearLayout = findViewById(R.id.linearlay4);
        tv_nodata = findViewById(R.id.tvnodata2);
    }

    @Override
    public void bindWidgetEvents() {

    }
    void setAdapter(){
        rcvPostfix.setAdapter(new PostfixAdapter(postfixeslist, this));
        adapter = new PostfixAdapter(postfixeslist, PostfixlistActivity.this);
        rcvPostfix.setAdapter(adapter);
        rcvPostfix.setLayoutManager(new GridLayoutManager(getApplicationContext(),
                1));
        rcvPostfix.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_postfixlist);
        super.onCreate(savedInstanceState);

        setTitle("History");
        if(postfixeslist.isEmpty()){
            tv_nodata.setVisibility(View.VISIBLE);
//            Toast.makeText(getApplicationContext(),
//                    "No Data Found", Toast.LENGTH_SHORT).show();
//            return;
        }
       setAdapter();
        swipe();
    }

    public void removeItem(int position) {
     Dal_EvaluatePostfix.getInstance().deleteString(postfixeslist.get(position).getSrNo());
        postfixeslist.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, postfixeslist.size());
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
                    new AlertDialog.Builder(PostfixlistActivity.this)
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
//                    Toast.makeText(getApplicationContext(),
//                            "Move to previous", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    Intent intent = new Intent(PostfixlistActivity.this, EvaluatePostfixActivity.class);
                    intent.putExtra("UserExp", postfixeslist.get(position).getUserExp());
                    intent.putExtra("Answer", postfixeslist.get(position).getAnswer());
                    startActivity(intent);
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
        itemTouchHelper.attachToRecyclerView(rcvPostfix);
    }

    private int convertDpToPx(int dp) {
        return Math.round(dp * (getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
