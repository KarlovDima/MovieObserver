package com.dima.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmDTO {
    private int id;
    private String name;
    private String duration;
    private boolean isActive;

}

