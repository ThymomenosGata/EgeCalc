package com.ilatis.egecalc.Data;

/**
 * Created by thymomenosgata on 21.02.18.
 */

public class ListForInterface {
    String disc;
    int ball;

    public ListForInterface(String disc, int ball) {
        this.disc = disc;
        this.ball = ball;
    }

    public String getDisc() {
        return disc;
    }

    public int getBall() {
        return ball;
    }
}
