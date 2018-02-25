package com.ilatis.egecalc.Parser.HelperClasses;

/**
 * Created by thymomenosgata on 26.02.18.
 */

public class ClassRaiting {
    String name;
    String proc;
    String money;

    public ClassRaiting(String name, String proc, String money) {
        this.name = name;
        this.proc = proc;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String getProc() {
        return proc;
    }

    public String getMoney() {
        return money;
    }

}
