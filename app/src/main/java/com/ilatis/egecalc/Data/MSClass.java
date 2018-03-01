package com.ilatis.egecalc.Data;

/**
 * Created by thymomenosgata on 01.03.18.
 */

public class MSClass {
    private String form;
    private String level;
    private String code;
    private double salary;
    private String name;
    private String full_name;
    private int places;
    private String common_name;
    private double cost;
    private ExamsClass exams;
    private String faculty;
    private int duration;
    private String ucheba_url;
    private double employment;
    private int score;

    public MSClass(String form, String level, String code, double salary, String name, String full_name, int places, String common_name, double cost, ExamsClass exams, String faculty, int duration, String ucheba_url, double employment, int score) {
        this.form = form;
        this.level = level;
        this.code = code;
        this.salary = salary;
        this.name = name;
        this.full_name = full_name;
        this.places = places;
        this.common_name = common_name;
        this.cost = cost;
        this.exams = exams;
        this.faculty = faculty;
        this.duration = duration;
        this.ucheba_url = ucheba_url;
        this.employment = employment;
        this.score = score;
    }

    public String getForm() {
        return form;
    }

    public String getLevel() {
        return level;
    }

    public String getCode() {
        return code;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getPlaces() {
        return places;
    }

    public String getCommon_name() {
        return common_name;
    }

    public double getCost() {
        return cost;
    }

    public ExamsClass getExams() {
        return exams;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getDuration() {
        return duration;
    }

    public String getUcheba_url() {
        return ucheba_url;
    }

    public double getEmployment() {
        return employment;
    }

    public int getScore() {
        return score;
    }
}
