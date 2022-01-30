package com.aswdc_dsconversion.Design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.aswdc_dsconversion.Helper.DrawShapePosition;
import com.aswdc_dsconversion.Helper.Node;
import com.aswdc_dsconversion.Helper.Tree;

import java.util.ArrayList;

public class DrawDemoActivity extends BaseActivity {
    ArrayList<DrawShapePosition> drawShapePositions = new ArrayList<DrawShapePosition>();
    Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    @Override
    public void initVariables() {

    }

    @Override
    public void bindWidgetEvents() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(new MyView(this));
        super.onCreate(savedInstanceState);

    }

    public void drawNode(Node node, Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setColor(Color.parseColor("#00796B"));

        canvas.drawCircle(node.xPos, node.yPos, node.radius, paint);
        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStrokeWidth(3);
        mTextPaint.setTextSize(40);
        mTextPaint.setColor(Color.WHITE);
        canvas.drawText(node.key + "", node.xPos, node.yPos + 20, mTextPaint);

        if (node.left != null) {
            drawNode(node.left, canvas);
        }
        if (node.right != null) {
            drawNode(node.right, canvas);
        }
    }

    public void checkNode(Node node, Canvas canvas) {
        if (node.left != null) {
            node.left.xPos = node.xPos - node.xJump;
            node.left.yPos = node.yPos + node.yJump;
            DrawShapePosition temp = new DrawShapePosition(node.left.xPos, node.left.yPos);

            for (DrawShapePosition d : drawShapePositions) {
                if (d.xPos == temp.xPos && d.yPos == temp.yPos) {
                    temp.xPos += node.xJump;
                    node.left.xPos += node.xJump;

                }
            }
            canvas.drawLine(node.xPos,node.yPos,node.left.xPos,node.left.yPos,linePaint);

            drawShapePositions.add(temp);
            checkNode(node.left,canvas);
        }
        if (node.right != null) {
            node.right.xPos = node.xPos + node.xJump;
            node.right.yPos = node.yPos + node.yJump;
            DrawShapePosition temp = new DrawShapePosition(node.right.xPos, node.right.yPos);
            for (DrawShapePosition d : drawShapePositions) {
                if (d.xPos == temp.xPos && d.yPos == temp.yPos) {
                    temp.xPos -= node.xJump;
                    node.right.xPos -= node.xJump;
                }
            }
            canvas.drawLine(node.xPos,node.yPos,node.right.xPos,node.right.yPos,linePaint);
            drawShapePositions.add(temp);
            checkNode(node.right,canvas);
        }
    }

    public class MyView extends View {

        public MyView(Context context) {
            super(context);
        }
        @Override
        protected void onDraw (Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            paint.setColor(Color.parseColor("#00796B"));
            Log.d("Size of an DNS", String.valueOf(Tree.dns));

//                for(int i=0;i<Tree.dns.size();i++){
//
//                    DrawLineShape temp2 = Tree.dls.get(i);
//                    Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//                    linePaint.setStrokeWidth(3);
//                    linePaint.setColor(Color.parseColor("#00796B"));
//                    canvas.drawLine(temp2.startX,temp2.startY, temp2.endX, temp2.endY, linePaint);
//
//                    Log.d("Line "+i,temp2.startX+ " -" +temp2.startY+ "-" +temp2.endX+ " -" +temp2.endY+"Testing123");
//
//                }




               /*
                started @ 09/08/2019
               for(int i=0;i<Tree.dns.size();i++){

                    DrawNodeShape temp = Tree.dns.get(i);
                    canvas.drawCircle(temp.centerX,temp.centerY,temp.radius,paint);
                    Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    mTextPaint.setTextAlign(Paint.Align.CENTER);
                    mTextPaint.setStrokeWidth(3);
                    mTextPaint.setTextSize(40);
                    mTextPaint.setColor(Color.WHITE);
                    canvas.drawText(temp.txtValue,temp.centerX,temp.centerY+20,mTextPaint);


                }*/

            linePaint.setStrokeWidth(3);
            linePaint.setColor(Color.parseColor("#00796B"));
            checkNode(Tree.root, canvas);
            drawNode(Tree.root, canvas);

            drawShapePositions.clear();
        }
    }

}