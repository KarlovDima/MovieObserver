package com.dima.models;

public class Critic {
    private int id;
    private String name;
    private String workBeginning;
    private String workEnding;

    public Critic() {
    }

    public Critic(int id, String name, String workBeginning, String workEnding) {
        this.id = id;
        this.name = name;
        this.workBeginning = workBeginning;
        this.workEnding = workEnding;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkBeginning() {
        return workBeginning;
    }

    public void setWorkBeginning(String workBeginning) {
        this.workBeginning = workBeginning;
    }

    public String getWorkEnding() {
        return workEnding;
    }

    public void setWorkEnding(String workEnding) {
        this.workEnding = workEnding;
    }
}
