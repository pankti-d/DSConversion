package com.aswdc_dsconversion.Bean;

import java.io.Serializable;

public class Bean_String implements Serializable {
    private String SrNo;
    private String UserString;
    private String Answer;

    public String getConversionType() {
        return ConversionType;
    }

    public void setConversionType(String conversionType) {
        ConversionType = conversionType;
    }

    private String ConversionType;

    public String getUserString() {
        return UserString;
    }

    public void setUserString(String userString) {
        UserString = userString;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }
}
