package com.ilatis.egecalc.ListAdapters;

/**
 * Created by thymomenosgata on 20.02.18.
 */

public class ListForEge {
    String univers;
    String disvipl;
    String special;
    int ball;
    int money;

    public ListForEge(String univers, String disvipl, String special, int ball, int money) {
        this.univers = univers;
        this.disvipl = disvipl;
        this.special = special;
        this.ball = ball;
        this.money = money;
    }

    public ListForEge() {
    }

    public String getUnivers() {
        return univers;
    }

    public String getDisvipl() {
        return disvipl;
    }

    public String getSpecial() {
        return special;
    }

    public int getBall() {
        return ball;
    }

    public int getMoney() {
        return money;
    }

    public void setDisvipl(String disvipl) {
        this.disvipl = disvipl;
    }
}
