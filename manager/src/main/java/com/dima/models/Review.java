package com.dima.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Review {
    private int id;
    private int filmId;
    private int criticId;
    private String comment;
}
