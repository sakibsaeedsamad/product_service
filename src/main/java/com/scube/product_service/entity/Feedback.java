package com.scube.product_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @SequenceGenerator(
            name = "feedbackId",
            sequenceName = "feedbackId",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "feedbackId"
    )
//@Id
//@GeneratedValue(
//        strategy = GenerationType.IDENTITY,
//        generator = "feedbackId"
//)
    private long feedbackId;
    @Column(nullable = false)
    private String feedbackRating;
    @Column(nullable = false)
    private String feedbackComment;
    @Column(nullable = false)
    public String feedbackDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String feedbackUserId;
}
