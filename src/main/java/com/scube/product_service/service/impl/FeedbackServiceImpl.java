package com.scube.product_service.service.impl;

import com.scube.product_service.entity.Feedback;
import com.scube.product_service.entity.Product;
import com.scube.product_service.exception.ProductServiceException;
import com.scube.product_service.exception.ResourceNotFoundException;
import com.scube.product_service.payload.FeedbackDto;
import com.scube.product_service.repository.FeedbackRepository;
import com.scube.product_service.repository.ProductRepository;
import com.scube.product_service.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    FeedbackRepository feedbackRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.now();

    @Override
    public FeedbackDto createFeedback(long productId, FeedbackDto feedbackDto) {
        Feedback feedback = mapToEntity(feedbackDto);
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","id",productId));
        feedback.setProduct(product);


        feedback.setFeedbackDate(dateTime.toString());

        //save feedback to db
        Feedback feedback1 = feedbackRepository.save(feedback);

        log.info("Inside createFeedback of FeedbackService");

        return mapToFeedbackDto(feedback1);
    }

    @Override
    public List<FeedbackDto> getAllFeedbackByProductId(long productId) {
        //retrive feedback by productId
        List<Feedback> feedbacks = feedbackRepository.findByproductId(productId);

        log.info("Inside getAllFeedbackByProductId of FeedbackService");

        //convert list of feedback entities to list of feedbackDto
        return feedbacks.stream().map(feedback ->
                mapToFeedbackDto(feedback)).collect(Collectors.toList());
    }

    @Override
    public FeedbackDto getFeedbackById(long productId, long feedbackId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","id",productId));

        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback","id",feedbackId));

        if (feedback.getProduct().getProductId() != (product.getProductId())){
            throw new ProductServiceException(HttpStatus.BAD_REQUEST,"Feedback is not from this product");
        }

        log.info("Inside getFeedbackById of FeedbackService");
        return mapToFeedbackDto(feedback);
    }

    @Override
    public FeedbackDto updateFeedbackById(long productId, long feedbackId, FeedbackDto feedbackDto) {

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","id",productId));

        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback","id",feedbackId));

        if (feedback.getProduct().getProductId() != (product.getProductId())){
            throw new ProductServiceException(HttpStatus.BAD_REQUEST,"Feedback is not from this product");
        }
        feedback.setFeedbackComment(feedbackDto.getFeedbackComment());
        feedback.setFeedbackRating(feedbackDto.getFeedbackRating());
        feedback.setFeedbackUserId(feedback.getFeedbackUserId());
        feedback.setFeedbackDate(dateTime.toString());

        Feedback feedback1 = feedbackRepository.save(feedback);

        log.info("Inside updateFeedbackById of FeedbackService");

        return mapToFeedbackDto(feedback1);
    }

    @Override
    public void deleteFeedbackById(long productId, long feedbackId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","id",productId));

        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback","id",feedbackId));

        if (feedback.getProduct().getProductId() != (product.getProductId())){
            throw new ProductServiceException(HttpStatus.BAD_REQUEST,"Feedback is not from this product");
        }

        log.info("Inside deleteFeedbackById of FeedbackService");

        feedbackRepository.delete(feedback);
    }


    public FeedbackDto mapToFeedbackDto(Feedback feedback){
        FeedbackDto feedbackDto = new FeedbackDto();

        feedbackDto.setFeedbackId(feedback.getFeedbackId());
        feedbackDto.setFeedbackComment(feedback.getFeedbackComment());
        feedbackDto.setFeedbackRating(feedback.getFeedbackRating());
        feedbackDto.setFeedbackDate(feedback.getFeedbackDate());
        feedbackDto.setFeedbackUserId(feedback.getFeedbackUserId());

        return feedbackDto;
    }

    public Feedback mapToEntity(FeedbackDto feedbackDto){
        Feedback feedback = new Feedback();
        feedback.setFeedbackComment(feedbackDto.getFeedbackComment());
        feedback.setFeedbackRating(feedbackDto.getFeedbackRating());
        feedback.setFeedbackUserId(feedbackDto.getFeedbackUserId());

        return  feedback;
    }
}
