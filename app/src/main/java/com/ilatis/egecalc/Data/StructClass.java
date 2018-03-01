package com.ilatis.egecalc.Data;

/**
 * Created by thymomenosgata on 01.03.18.
 */

import java.util.List;

public class StructClass {
    private double continued_amount;
    private int num_foreigners;
    private int num_graduates;
    private String monitoring2;
    private int num_continued;
    private String district_name;
    private int num_working;
    private String name;
    private double avg_wage;
    private int num_employed;
    private List<MSClass> programs;
    private String ucheba_url;
    private double working_percent2;
    private String region_name;
    private int num_enterpreneurs;
    private int type_id;
    private int num_snils_not_found;
    private String id;
    private String unreliable;
    private int num_empty_name;
    private int num_snils_ok;
    private int num_snils_ambiguous;
    private int num_wrong_birthday;
    private double avg_age;
    private int num_total;
    private int num_regions;
    private double working_percent;
    private int num_approved;

    public StructClass(double continued_amount, int num_foreigners, int num_graduates, String monitoring2, int num_continued, String district_name, int num_working, String name, double avg_wage, int num_employed, List<MSClass> programs, String ucheba_url, double working_percent2, String region_name, int num_enterpreneurs, int type_id, int num_snils_not_found, String id, String unreliable, int num_empty_name, int num_snils_ok, int num_snils_ambiguous, int num_wrong_birthday, double avg_age, int num_total, int num_regions, double working_percent, int num_approved) {
        this.continued_amount = continued_amount;
        this.num_foreigners = num_foreigners;
        this.num_graduates = num_graduates;
        this.monitoring2 = monitoring2;
        this.num_continued = num_continued;
        this.district_name = district_name;
        this.num_working = num_working;
        this.name = name;
        this.avg_wage = avg_wage;
        this.num_employed = num_employed;
        this.programs = programs;
        this.ucheba_url = ucheba_url;
        this.working_percent2 = working_percent2;
        this.region_name = region_name;
        this.num_enterpreneurs = num_enterpreneurs;
        this.type_id = type_id;
        this.num_snils_not_found = num_snils_not_found;
        this.id = id;
        this.unreliable = unreliable;
        this.num_empty_name = num_empty_name;
        this.num_snils_ok = num_snils_ok;
        this.num_snils_ambiguous = num_snils_ambiguous;
        this.num_wrong_birthday = num_wrong_birthday;
        this.avg_age = avg_age;
        this.num_total = num_total;
        this.num_regions = num_regions;
        this.working_percent = working_percent;
        this.num_approved = num_approved;
    }

    public double getContinued_amount() {
        return continued_amount;
    }

    public int getNum_foreigners() {
        return num_foreigners;
    }

    public int getNum_graduates() {
        return num_graduates;
    }

    public String getMonitoring2() {
        return monitoring2;
    }

    public int getNum_continued() {
        return num_continued;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public int getNum_working() {
        return num_working;
    }

    public String getName() {
        return name;
    }

    public double getAvg_wage() {
        return avg_wage;
    }

    public int getNum_employed() {
        return num_employed;
    }

    public List<MSClass> getPrograms() {
        return programs;
    }

    public String getUcheba_url() {
        return ucheba_url;
    }

    public double getWorking_percent2() {
        return working_percent2;
    }

    public String getRegion_name() {
        return region_name;
    }

    public int getNum_enterpreneurs() {
        return num_enterpreneurs;
    }

    public int getType_id() {
        return type_id;
    }

    public int getNum_snils_not_found() {
        return num_snils_not_found;
    }

    public String getId() {
        return id;
    }

    public String getUnreliable() {
        return unreliable;
    }

    public int getNum_empty_name() {
        return num_empty_name;
    }

    public int getNum_snils_ok() {
        return num_snils_ok;
    }

    public int getNum_snils_ambiguous() {
        return num_snils_ambiguous;
    }

    public int getNum_wrong_birthday() {
        return num_wrong_birthday;
    }

    public double getAvg_age() {
        return avg_age;
    }

    public int getNum_total() {
        return num_total;
    }

    public int getNum_regions() {
        return num_regions;
    }

    public double getWorking_percent() {
        return working_percent;
    }

    public int getNum_approved() {
        return num_approved;
    }
}