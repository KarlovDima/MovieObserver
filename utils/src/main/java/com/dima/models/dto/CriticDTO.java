package com.dima.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriticDTO {
    private int id;
    private String name;
    private String workBeginning;
    private String workEnding;
    private String host;
    private int port;
}
