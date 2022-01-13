package com.scube.product_service.controller;

import com.scube.product_service.payload.FeedbackDto;
import com.scube.product_service.payload.ProductDto;
import com.scube.product_service.service.FeedbackService;
import com.scube.product_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/product/{productId}/feedBack")
    public FeedbackDto createFeedback(@PathVariable("productId") Long productId,
                                     @RequestBody FeedbackDto feedbackDto) {
        log.info("Inside the createFeedback Controller");

        return feedbackService.createFeedback(productId, feedbackDto);
    }

    @GetMapping("/product/{productId}/feedBack")
    public List<FeedbackDto> getAllFeedbackByProductId(@PathVariable("productId") Long productId) {

        log.info("Inside the getAllFeedbackByProductId Controller");

        return feedbackService.getAllFeedbackByProductId(productId);
    }

    @PutMapping("/product/{productId}/feedBack/{feedbackId}")
    public FeedbackDto updateFeedback(@PathVariable("productId") Long productId,
                                    @PathVariable("feedbackId") Long feedbackId,
                                    @RequestBody FeedbackDto feedbackDto) {

        log.info("Inside the updateFeedback Controller");

        return feedbackService.updateFeedbackById(productId, feedbackId, feedbackDto);

    }

    @DeleteMapping("/product/{productId}/feedBack/{feedbackId}")
    public String deleteFeedbackById(@PathVariable("productId") Long productId,
                                      @PathVariable("feedbackId") Long feedbackId) {

        log.info("Inside the deleteFeedbackById Controller");

        feedbackService.deleteFeedbackById(productId, feedbackId);

        return "Feedback Deleted Successfully";
    }
}
