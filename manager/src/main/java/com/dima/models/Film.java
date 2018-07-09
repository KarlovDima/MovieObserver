package com.dima.models;

public class Film {
    private int id;
    private String name;
    private int duration;
    private boolean isActive;

    public Film() {
    }

    public Film(int id, String name, int duration, boolean isActive) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.isActive = isActive;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
