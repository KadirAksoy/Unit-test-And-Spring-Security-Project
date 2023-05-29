package com.kadiraksoy.UnittestAndSpringSecurityProject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rewiev")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rewiev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private int stars;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "misket_id")
    private Misket misket;
}
