package com.ivanskodje.dependency.demo.dependencydemo.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity(name = "item")
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Item(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
