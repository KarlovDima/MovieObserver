package com.dima.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
    private int id;
    private int filmId;
    private int criticId;
    private String comment;
}
