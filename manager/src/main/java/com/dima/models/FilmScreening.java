package com.dima.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmScreening {
    private int id;
    private int filmId;
    private String time;
}
