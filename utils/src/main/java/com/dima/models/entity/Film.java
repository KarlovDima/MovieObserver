package com.dima.models.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<FilmScreening> filmScreenings;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<Review> reviews;
}
