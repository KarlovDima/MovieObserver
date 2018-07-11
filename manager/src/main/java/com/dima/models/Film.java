package com.dima.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Film {
    private int id;
    private String name;
    private int duration;
    private boolean isActive;
}
