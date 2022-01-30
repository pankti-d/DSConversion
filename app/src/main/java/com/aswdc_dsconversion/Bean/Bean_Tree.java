package com.aswdc_dsconversion.Bean;

import java.io.Serializable;

public class Bean_Tree implements Serializable {
    private String SrNo;
    private String UserTree;
    private String InOrder;

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getUserTree() {
        return UserTree;
    }

    public void setUserTree(String userTree) {
        UserTree = userTree;
    }

    public String getInOrder() {
        return InOrder;
    }

    public void setInOrder(String inOrder) {
        InOrder = inOrder;
    }
}
