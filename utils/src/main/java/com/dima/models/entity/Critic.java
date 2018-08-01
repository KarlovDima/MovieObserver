package com.dima.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "critic")
public class Critic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "work_beginning", nullable = false)
    private String workBeginning;

    @Column(name = "work_ending", nullable = false)
    private String workEnding;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "critic", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Review> reviews;

    @Column(name = "host")
    private String host;

    @Column(name = "port")
    private int port;
}
