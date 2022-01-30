package com.aswdc_dsconversion.Bean;

import java.io.Serializable;

public class Bean_Examples implements Serializable {

    private String SrNo;
    private String Example;
    private String html;

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getExample() {
        return Example;
    }

    public void setExample(String example) {
        Example = example;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
