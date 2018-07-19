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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "critic")
    private List<Review> reviews;

    @Column(name = "host", nullable = false)
    private String host;

    @Column(name = "port", nullable = false)
    private int port;
}
