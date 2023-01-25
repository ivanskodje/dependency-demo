package com.ivanskodje.dependency.demo.dependencydemo.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class Item {

    // Name is unique, and what we happened to choose as our domain/business identifier
    // (we dont know anything about what databases use, and we dont care)
    private String name;

    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
