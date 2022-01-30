package com.aswdc_dsconversion.Design;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;

import com.aswdc_dsconversion.DBHelper.Dal_TreeHistory;
import com.aswdc_dsconversion.Helper.Tree;
import com.aswdc_dsconversion.R;

import java.util.ArrayList;
import java.util.Arrays;

public class AddNodeActivity extends BaseActivity {
    private static final String TAG = "AddNodeActivity";

    Button btn_add, btn_clear, btn_done, btn_generate;
    TextView tv_nodes, tv_inorder, tv_preorder, tv_postorder;
    CardView cvtreeresult;
    EditText et_Node;
    String InputNode, tvnode;
    ArrayList<Integer> TreeNodes = new ArrayList<>();
    Integer[] treeNode;
    String[] treeinsert;
    Tree t = new Tree();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history, menu);
        // getMenuInflater().inflate(R.menu.his, menu);
        return true;
    }


    @Override
    public void initVariables() {
        btn_add = findViewById(R.id.ac_btn_add);
        btn_clear = findViewById(R.id.ac_btn_clear);
        btn_generate = findViewById(R.id.ac_btn_displaytree);
        btn_done = findViewById(R.id.ac_btn_GenerateTree);
        et_Node = findViewById(R.id.ac_et_Node);
        tv_nodes = findViewById(R.id.tv_nodes);
        tv_inorder=findViewById(R.id.ac_tv_inorder);
        tv_preorder=findViewById(R.id.ac_tv_treepreorder);
        tv_postorder=findViewById(R.id.ac_tv_treepostorder);
        cvtreeresult=findViewById(R.id.cv_Treeresult);
    }

    @Override
    public void bindWidgetEvents() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputNode = et_Node.getText().toString();
                if (InputNode.matches("")) {
                    Toast.makeText(getApplicationContext(),
                            "Enter a Number", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    tv_nodes.setVisibility(View.VISIBLE);
                    int node = Integer.parseInt(InputNode);
                    if(TreeNodes.contains(node)){
                        Toast.makeText(getApplicationContext(),
                                "Cannot enter duplicate value", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        TreeNodes.add(node);
                        tv_nodes.setText(tv_nodes.getText() + " " + InputNode);
                    }
                }
                et_Node.setText("");
            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                tvnode = tv_nodes.getText()+"";
                if(tvnode.matches("")){
                    Toast.makeText(getApplicationContext(),
                            "Please Enter a Node", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    cvtreeresult.setVisibility(View.VISIBLE);
                    treeNode = TreeNodes.toArray(new Integer[0]);
                    treeinsert = new String[treeNode.length];
                    for (int i = 0; i < treeNode.length; i++) {
                        treeinsert[i] = String.valueOf(treeNode[i]);
                    }
                    t.Insert(treeNode);
                    tv_inorder.setText(t.inOrderPrint(t.root));
                    tv_preorder.setText(t.preOrderPrint(t.root));
                    tv_postorder.setText(t.postOrderPrint(t.root));
                    if(treeinsert!=null){insertContent();}


                }
//                else{Intent in = new Intent(AddNodeActivity.this, TreeDisplay.class);
//                in.putExtra("TreeNode", TreeNodes);
//                startActivity(in);}

            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvtreeresult.setVisibility(View.GONE);
                tv_nodes.setVisibility(View.GONE);
                TreeNodes.clear();
                tv_nodes.setText("");
                et_Node.setText("");

            }
        });

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dls.clear();
                TreeNodes.clear();
//                Tree.dns.clear();
                Intent intent = new Intent(AddNodeActivity.this, DrawDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addnode);

        super.onCreate(savedInstanceState);
        Tree.dns.clear();
        Tree.dls.clear();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Binary Tree");

        if(getIntent().hasExtra("UserTree")) {
            String usertree = getIntent().getStringExtra("UserTree");
            String intertree = usertree.substring(1, usertree.length()-1);
            treeinsert = intertree.split(",");
            treeNode = new Integer[treeinsert.length];
            for (int i = 0; i < treeinsert.length; i++) {
                treeNode[i] = Integer.parseInt(treeinsert[i].trim());
            }
            tv_nodes.setVisibility(View.VISIBLE);
            tv_nodes.setText(usertree);
            t.Insert(treeNode);
            tv_inorder.setText(t.inOrderPrint(t.root));
            tv_preorder.setText(t.preOrderPrint(t.root));
            tv_postorder.setText(t.postOrderPrint(t.root));
            cvtreeresult.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Intent intent = new Intent(AddNodeActivity.this, TreelistActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void insertContent() {
        ContentValues cv = new ContentValues();
        cv.put(Dal_TreeHistory._UserTree, Arrays.toString(treeinsert));
        cv.put(Dal_TreeHistory._inorder, tv_inorder.getText().toString());
        Dal_TreeHistory.getInstance().insertContent(cv);
    }
}
