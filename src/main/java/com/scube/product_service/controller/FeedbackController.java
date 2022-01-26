package com.scube.product_service.controller;

import com.scube.product_service.payload.FeedbackDto;
import com.scube.product_service.payload.ProductDto;
import com.scube.product_service.service.FeedbackService;
import com.scube.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @Operation(summary = "This is to save a feedback stored in Db for a unique product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved feedback from Db for a unique product.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Product not found with id : productId.",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping("/products/{productId}/feedbacks")
    public FeedbackDto createFeedback(@PathVariable("productId") long productId,
                                      @Valid @RequestBody FeedbackDto feedbackDto) {
        log.info("Inside the createFeedback Controller");

        return feedbackService.createFeedback(productId, feedbackDto);
    }

    @Operation(summary = "This is to fetch all feedback stored in Db for a unique product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched feedbacks stored in Db for a unique product.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Feedback is not from this product",
                    content = {@Content(mediaType = "application/json")}),
    })

    @GetMapping("/products/{productId}/feedbacks")
    public List<FeedbackDto> getAllFeedbackByProductId(@PathVariable("productId") long productId) {

        log.info("Inside the getAllFeedbackByProductId Controller");

        return feedbackService.getAllFeedbackByProductId(productId);

    }

    @Operation(summary = "This is to update unique feedback stored in Db for a unique product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated feedback form Db for a unique product.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Feedback not found with id : feedbackId.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Feedback is not from this product.",
                    content ={@Content(mediaType = "application/json")}),
    })

    @PutMapping("/products/{productId}/feedbacks/{feedbackId}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable("productId") long productId,
                                                      @PathVariable("feedbackId") long feedbackId,
                                                      @Valid @RequestBody FeedbackDto feedbackDto) {

        log.info("Inside the updateFeedback Controller");

        FeedbackDto feedbackDtoResponse = feedbackService.updateFeedbackById(productId, feedbackId, feedbackDto);
        return new ResponseEntity<>(feedbackDtoResponse, HttpStatus.OK);

    }

    @Operation(summary = "This is to fetch unique feedback stored in Db for a unique product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched feedback form Db for a unique product.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Feedback not found with id : feedbackId.",
                    content ={@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Feedback is not from this product.",
                    content = {@Content(mediaType = "application/json")}),
    })

    @GetMapping("/products/{productId}/feedbacks/{feedbackId}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable("productId") long productId,
                                                       @PathVariable("feedbackId") long feedbackId) {

        log.info("Inside the updateFeedback Controller");

        FeedbackDto feedbackDtoResponse = feedbackService.getFeedbackById(productId, feedbackId);
        return new ResponseEntity<>(feedbackDtoResponse, HttpStatus.OK);

    }

    @Operation(summary = "This is to delete a unique feedback stored in Db for a unique product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Feedback deleted successfully.",
                    content = {@Content(mediaType = "text/plain;charset=UTF-8")}),
            @ApiResponse(responseCode = "404",
                    description = "Feedback not found with id : feedbackId.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Feedback is not from this product.",
                    content = {@Content(mediaType = "application/json")}),
    })

    @DeleteMapping("/products/{productId}/feedbacks/{feedbackId}")
    public ResponseEntity<String> deleteFeedbackById(@PathVariable("productId") long productId,
                                                     @PathVariable("feedbackId") long feedbackId) {

        log.info("Inside the deleteFeedbackById Controller");

        feedbackService.deleteFeedbackById(productId, feedbackId);

        return new ResponseEntity<>("Feedback Deleted Successfully", HttpStatus.OK);
    }
}
