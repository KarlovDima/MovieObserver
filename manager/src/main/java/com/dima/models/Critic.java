package com.dima.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Critic {
    private int id;
    private String name;
    private String workBeginning;
    private String workEnding;
}
