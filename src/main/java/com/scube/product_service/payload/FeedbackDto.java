package com.scube.product_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    private long feedbackId;

    @NotEmpty
    @Size(min = 1, message = "Rating should have 1 character.")
    private String feedbackRating;

    @NotEmpty
    @Size(min = 5, message = "Comment should have 5 characters.")
    private String feedbackComment;

    private String feedbackUserId;

    public String feedbackDate;


}
