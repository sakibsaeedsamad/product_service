package com.scube.product_service.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @SequenceGenerator(
            name = "categoryId",
            sequenceName = "categoryId",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "categoryId"
    )
//@Id
//@GeneratedValue(
//        strategy = GenerationType.IDENTITY,
//        generator = "categoryId"
//    )
    private long categoryId;

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();
}