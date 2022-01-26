package com.scube.product_service.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class FeedbackDto {

    private long feedbackId;

    @NotEmpty
    @Size(min = 1, message = "Rating should have 1 character.")
    private String feedbackRating;

    @NotEmpty
    @Size(min = 5, message = "Comment should have 5 characters.")
    private String feedbackComment;

    private String feedbackUserId;

    private String feedbackDate;


}
