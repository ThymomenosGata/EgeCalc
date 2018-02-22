package com.ilatis.egecalc.Parser.HelperClasses;

/**
 * Created by thymomenosgata on 18.02.18.
 */

public class ClassForUniversities {
    private String univerity;
    private String codeOfUn;
    private String speciality;
    private String disciplins;
    private int balsOf;
    private int yourBals;

    public ClassForUniversities(String univerity, String codeOfUn,
                                String speciality, String disciplins,
                                int balsOf, int yourBals) {
        this.univerity = univerity;
        this.codeOfUn = codeOfUn;
        this.speciality = speciality;
        this.disciplins = disciplins;
        this.balsOf = balsOf;
        this.yourBals = yourBals;
    }

    public String getUniverity() {
        return univerity;
    }

    public String getCodeOfUn() {
        return codeOfUn;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getDisciplins() {
        return disciplins;
    }

    public int getBalsOf() {
        return balsOf;
    }

    public int getYourBals() {
        return yourBals;
    }
}
