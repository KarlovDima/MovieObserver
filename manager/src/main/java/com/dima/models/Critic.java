package com.dima.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Critic {
    private int id;
    private String name;
    private String workBeginning;
    private String workEnding;
}
