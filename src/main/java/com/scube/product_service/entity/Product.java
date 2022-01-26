package com.scube.product_service.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @SequenceGenerator(
            name = "productId",
            sequenceName = "productId",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "productId"
    )
//@Id
//@GeneratedValue(
//        strategy = GenerationType.IDENTITY,
//        generator = "productId"
//)
    public long productId;

    @Column(nullable = false)
    public String productName;

    @Column(nullable = false)
    public String productDescription;

    @Column(nullable = false)
    public String productCreateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Feedback> feedback = new HashSet<>();


}