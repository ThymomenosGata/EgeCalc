package com.ilatis.egecalc.Data;

/**
 * Created by thymomenosgata on 01.03.18.
 */

import java.util.List;

public class ExamsClass {
    private List<String> ege;
    private String custom;

    public ExamsClass(List<String> ege, String custom) {
        this.ege = ege;
        this.custom = custom;
    }

    public List<String> getEge() {
        return ege;
    }

    public String getCustom() {
        return custom;
    }
}