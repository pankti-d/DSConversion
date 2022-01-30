package com.aswdc_dsconversion.Helper;

/* Java program to construct binary tree from given array in level order fashion */

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;



public class Tree implements Serializable {


    public static Node root;
    public static float indexD = 0, indexLeft=0, indexTop=0;
    public static ArrayList<DrawNodeShape> dns = new ArrayList<DrawNodeShape>();
    public static ArrayList<DrawLineShape> dls = new ArrayList<DrawLineShape>();
    public int yStep = 150;

    int indexCounterForDns = 0;


    public int getStepX(int level) {
        if (level == 0) {
            return 200;
        } else if (level == 1) {
            return 150;
        } else if (level == 2) {
            return 100;
        } else {
            return 50;
        }
    }

    //Constructor
    public Tree() {
        root = null;
    }

    //This method gets value from inserted array
    public void Insert(Integer[] arr) {
        dls.clear();
        dns.clear();
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            indexLeft=0;
            indexTop = 0;
            root = insertRec(root, key,"");
        }
    }

    //recursive function to insert a new key in BST
    public Node insertRec(Node root, int key, String isLeft ) {

        //If the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            DrawNodeShape temp = new DrawNodeShape();
            DrawLineShape temp2 = new DrawLineShape();
            /*boolean isRepeat = false;
            for (int i=0;i<dns.size();i++){
                if((dns.get(i).centerX==root.xPos + (int)(indexLeft * xStep)) && (dns.get(i).centerY==root.yPos + (int)(indexTop * yStep))){
                    *//*if(isLeft.equals("yes")) {
                        dns.get(i).centerX += root.xPos + (int) (0.5 * xStep);
                    }
                    if(isLeft.equals("no")) {
                        dns.get(i).centerX -= root.xPos + (int) (0.5 * xStep);
                    }*//*
                    isRepeat=true;
                }
            }
            if(isRepeat && isLeft.equals("yes")) {
                indexLeft+=0.7;
                isRepeat =  false;
            }
            else if(isRepeat && isLeft.equals("no")){
                indexLeft-=0.7;
                isRepeat = false;
            }*/

            temp.isLeft = isLeft.equals("yes") ? true : false;
            temp.childOfNode = root.insertedIndex;
            temp.insertedIndex =

            /*if(Tree.dns.size()==0){
                temp.centerX = 500 + (int)(indexLeft * xStep);
                temp.centerY = 100 + (int)(indexTop*yStep);
            }
            else{
                for(int i=0;i<Tree.dns.size();i++){
                    temp.centerX = Tree.dns.get(root.insertedIndex).centerX + (int)(indexLeft * xStep);
                    temp.centerY = Tree.dns.get(root.insertedIndex).centerY + (int)(indexTop*yStep);
                    if(Tree.dns.get(i).centerX==temp.centerX && Tree.dns.get(i).centerY==temp.centerY){
                        if(temp.isLeft){
                            temp.centerX += 75;
                            Tree.dns.get(i).centerX -= 75;
                        }
                        else{
                            temp.centerX -= 75;
                            Tree.dns.get(i).centerX += 75;
                        }
                    }
                }

            }*/

            temp.centerX = root.xPos + (int) (indexLeft * getStepX((int) indexTop));
            temp.centerY = root.yPos + (int)(indexTop*yStep);


            temp.txtValue = key+"";
            if(isLeft.equals("yes")){
                temp2.startX = Tree.dns.get(root.insertedIndex).centerX + ((indexLeft + 1) * getStepX((int) indexTop));
                temp2.startY=root.yPos+((indexTop-1)*yStep);
                temp2.endX = root.xPos + indexLeft * getStepX((int) indexTop);
                temp2.endY= root.yPos +indexTop*yStep;
            }
            else if(isLeft.equals("no")){
                temp2.startX = root.xPos + ((indexLeft - 1) * getStepX((int) indexTop));
                temp2.startY=root.yPos+((indexTop-1)*yStep);
                temp2.endX = root.xPos + indexLeft * getStepX((int) indexTop);
                temp2.endY= root.yPos +indexTop*yStep;
            }


            dls.add(temp2);
            dns.add(temp);
            root.insertedIndex = indexCounterForDns;
            indexCounterForDns++;
            /*for(int i=0;i<Tree.dns.size()-1;i++){
                for(int j=i+1;j<Tree.dns.size();j++){
                    if(Tree.dns.get(i).centerX==Tree.dns.get(j).centerX && Tree.dns.get(i).centerY==Tree.dns.get(j).centerY){
                        if(Tree.dns.get(j).isLeft){
                            Tree.dns.get(j).centerX += 75;
                            Tree.dns.get(i).centerX -= 75;
                        }
                        else{
                            Tree.dns.get(j).centerX -= 75;
                            Tree.dns.get(i).centerX += 75;
                        }
                    }
                }
            }*/
            return root;
        }
        //Otherwise, recur down the tree
        if (key < root.key) {
            Log.d("Inasaa",root.key+": Left");
            indexLeft--;
            indexTop++;
            root.left = insertRec(root.left, key,"yes");
        }
        else if (key > root.key) {
            indexLeft++;
            indexTop++;
            root.right = insertRec(root.right, key,"no");


        }
        //return the node pointer
        return root;
    }

    public String inOrderPrint(Node root)
    {
        ArrayList<Integer> TreeDisplay = new ArrayList<>();
        String result = inOrder(root , TreeDisplay);
        return result;
    }
    // Function to print tree nodes in InOrder fashion
    public String inOrder(Node root, ArrayList<Integer> TreeDisplay) {
        if (root != null) {
            inOrder(root.left, TreeDisplay);
            TreeDisplay.add(root.key);
            inOrder(root.right, TreeDisplay);
        }
        String InorderDisplay = TreeDisplay.toString();
        return InorderDisplay;
    }


    public String preOrderPrint(Node root)
    {
        ArrayList<Integer> TreeDisplay = new ArrayList<>();
        String result = preOrder(root , TreeDisplay);
        return result;
    }


    public String preOrder(Node root, ArrayList<Integer> TreeDisplay) {
        if (root != null) {
            TreeDisplay.add(root.key);
            preOrder(root.left ,TreeDisplay);
            preOrder(root.right, TreeDisplay);
        }
        String PreorderDisplay = TreeDisplay.toString();
        return PreorderDisplay;
    }

    public String postOrderPrint(Node root)
    {
        ArrayList<Integer> TreeDisplay = new ArrayList<>();
        String result = postOrder(root , TreeDisplay);
        return result;
    }

    public String postOrder(Node root, ArrayList<Integer> TreeDisplay) {
        if (root != null) {
            postOrder(root.left, TreeDisplay);
            postOrder(root.right, TreeDisplay);
            TreeDisplay.add(root.key);
        }
        String PostorderDisplay = TreeDisplay.toString();
        return PostorderDisplay;
    }
}
