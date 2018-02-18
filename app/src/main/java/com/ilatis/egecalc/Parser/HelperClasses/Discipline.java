package com.ilatis.egecalc.Parser.HelperClasses;

/**
 * Created by thymomenosgata on 18.02.18.
 */

public class Discipline {
    private Boolean obsh;
    private Boolean rus;
    private Boolean ikt;
    private Boolean bio;
    private Boolean geo;
    private Boolean him;
    private Boolean fiz;
    private Boolean lit;
    private Boolean ist;
    private Boolean mat;
    private Boolean inyz;

    public Discipline() {
        this.obsh = false;
        this.rus = false;
        this.ikt = false;
        this.bio = false;
        this.geo = false;
        this.him = false;
        this.fiz = false;
        this.lit = false;
        this.ist = false;
        this.mat = false;
        this.inyz = false;
    }

    public Boolean getObsh() {
        return obsh;
    }

    public void setObsh(Boolean obsh) {
        this.obsh = obsh;
    }

    public Boolean getRus() {
        return rus;
    }

    public void setRus(Boolean rus) {
        this.rus = rus;
    }

    public Boolean getIkt() {
        return ikt;
    }

    public void setIkt(Boolean ikt) {
        this.ikt = ikt;
    }

    public Boolean getBio() {
        return bio;
    }

    public void setBio(Boolean bio) {
        this.bio = bio;
    }

    public Boolean getGeo() {
        return geo;
    }

    public void setGeo(Boolean geo) {
        this.geo = geo;
    }

    public Boolean getHim() {
        return him;
    }

    public void setHim(Boolean him) {
        this.him = him;
    }

    public Boolean getFiz() {
        return fiz;
    }

    public void setFiz(Boolean fiz) {
        this.fiz = fiz;
    }

    public Boolean getLit() {
        return lit;
    }

    public void setLit(Boolean lit) {
        this.lit = lit;
    }

    public Boolean getIst() {
        return ist;
    }

    public void setIst(Boolean ist) {
        this.ist = ist;
    }

    public Boolean getMat() {
        return mat;
    }

    public void setMat(Boolean mat) {
        this.mat = mat;
    }

    public Boolean getInyz() {
        return inyz;
    }

    public void setInyz(Boolean inyz) {
        this.inyz = inyz;
    }
}
