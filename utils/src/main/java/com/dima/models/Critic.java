package com.dima.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "critic", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "critic-review")
    private List<Review> reviews;
}
