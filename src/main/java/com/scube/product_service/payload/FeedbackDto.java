package com.scube.product_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    private long feedbackId;

    private String feedbackRating;
    private String feedbackComment;
    private String feedbackUserId;

    public String feedbackDate;


}
