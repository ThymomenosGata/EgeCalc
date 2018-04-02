package com.ilatis.egecalc.Data;

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

    public void setProc(String proc) {
        this.proc = proc;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
