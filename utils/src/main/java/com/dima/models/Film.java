package com.dima.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "film")
    private List<FilmScreening> filmScreenings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "film")
    private List<Review> reviews;

    @Column(name = "host")
    private String host;

    @Column(name = "port")
    private int port;

}
