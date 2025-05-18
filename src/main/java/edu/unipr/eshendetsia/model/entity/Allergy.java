package edu.unipr.eshendetsia.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Allergy {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String allergen; //medikamenti

    @Column
    private String reaction; //pasoja/afterefekti

    @Column
    private String notes;
}
