package com.example.appsteam.motivision.ModelClass;

/**
 * Created by appsteam on 06-12-2017.
 */

public class Model_Motivibe_Activities {
    String act_name,act_des;
    int id;

    public Model_Motivibe_Activities(String act_name, String act_des) {
        this.act_name = act_name;
        this.act_des = act_des;

    }

    public Model_Motivibe_Activities() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model_Motivibe_Activities(int id, String act_name, String act_des) {
        this.id=id;
        this.act_name = act_name;
        this.act_des = act_des;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAct_des() {
        return act_des;
    }

    public void setAct_des(String act_des) {
        this.act_des = act_des;
    }
}
