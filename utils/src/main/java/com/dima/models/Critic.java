package com.dima.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Critic {
    private int id;
    private String name;
    private String workBeginning;
    private String workEnding;
    private String host;
    private int port;
}
