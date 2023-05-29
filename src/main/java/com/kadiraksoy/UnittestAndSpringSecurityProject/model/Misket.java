package com.kadiraksoy.UnittestAndSpringSecurityProject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "misket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Misket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;

    @OneToMany(mappedBy = "misket", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rewiev> rewievs = new ArrayList<>();
}
