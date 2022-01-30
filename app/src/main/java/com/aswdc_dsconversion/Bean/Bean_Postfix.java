package com.aswdc_dsconversion.Bean;

import java.io.Serializable;

public class Bean_Postfix implements Serializable {
    private String SrNo;
    private String UserExp;
    private String Answer;

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getUserExp() {
        return UserExp;
    }

    public void setUserExp(String userExp) {
        UserExp = userExp;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
