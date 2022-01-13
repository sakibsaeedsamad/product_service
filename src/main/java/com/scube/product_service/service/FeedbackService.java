package com.scube.product_service.service;

import com.scube.product_service.payload.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    FeedbackDto createFeedback(long productId,FeedbackDto feedbackDto);

    List<FeedbackDto> getAllFeedbackByProductId (long productId);

    FeedbackDto updateFeedbackById(long productId,long feedbackId,FeedbackDto feedbackDto);

    void deleteFeedbackById(long productId,long feedbackId);
}
