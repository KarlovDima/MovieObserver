package com.dima.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Film {
    private int id;
    private String name;
    private int duration;
    private boolean isActive;
}
