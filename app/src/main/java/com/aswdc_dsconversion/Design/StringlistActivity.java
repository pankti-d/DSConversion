package com.aswdc_dsconversion.Design;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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

import com.aswdc_dsconversion.Adapter.StinglistAdapter;
import com.aswdc_dsconversion.Bean.Bean_String;
import com.aswdc_dsconversion.DBHelper.Dal_StringHistory;
import com.aswdc_dsconversion.R;
import com.aswdc_dsconversion.bal.Bal_StringHistory;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class StringlistActivity extends BaseActivity {
    RecyclerView rcvString;
    TextView tv_nodata;
    StinglistAdapter adapter;
    ArrayList<Bean_String> stringslist;
    LinearLayout linearLayout;
    float ALPHA_FULL = 1.0f;

    @Override
    public void initVariables() {
        linearLayout = findViewById(R.id.stringview);
        rcvString = findViewById(R.id.rcvString);
        stringslist = Bal_StringHistory.getInstance().getString();
        tv_nodata = findViewById(R.id.tvnodata);
    }

    @Override
    public void bindWidgetEvents() {

    }
    void setAdapter(){
        rcvString.setAdapter(new StinglistAdapter(stringslist, this));
        adapter = new StinglistAdapter(stringslist, StringlistActivity.this);
        rcvString.setAdapter(adapter);
        rcvString.setLayoutManager(new GridLayoutManager(getApplicationContext(),
                1));
        rcvString.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_stringlist_view);

        super.onCreate(savedInstanceState);
        setTitle("History");
        if(stringslist.isEmpty()){
            tv_nodata.setVisibility(View.VISIBLE);

//            Toast.makeText(getApplicationContext(),
//                "No Data Found", Toast.LENGTH_SHORT).show();
//            return;
        }
       setAdapter();
        swipe();
    }


    public void removeItem(int position) {
        Dal_StringHistory.getInstance().deleteString(stringslist.get(position).getSrNo());
        stringslist.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, stringslist.size());
        Snackbar snackbar = Snackbar
                .make(linearLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);

        snackbar.setActionTextColor(Color.YELLOW);
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

                Log.d("Dir", swipeDir+"");
                if (swipeDir == 4 ) {
                    new AlertDialog.Builder(StringlistActivity.this)
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
                    Intent intent = new Intent(StringlistActivity.this, ConversionActivity.class);
                    intent.putExtra("UserString", stringslist.get(position).getUserString());
                    intent.putExtra("Type", stringslist.get(position).getConversionType());
                    intent.putExtra("Answer", stringslist.get(position).getAnswer());
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
        itemTouchHelper.attachToRecyclerView(rcvString);
    }

    private int convertDpToPx(int dp) {
        return Math.round(dp * (getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


}
