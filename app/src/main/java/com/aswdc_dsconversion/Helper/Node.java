package com.aswdc_dsconversion.Helper;

// Tree Node
public class Node {
    public int key;
    public Node left, right;
    public int xPos = 550, yPos = 100;
    public int xJump = 150, yJump = 150;
    public int insertedIndex = 0;
    public int radius = 70;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}