package com.target.customer.feedback.controller.impl;

import com.target.customer.feedback.service.bl.FeedbackService;
import com.target.customer.feedback.service.model.FeedbackRequest;
import com.target.customer.feedback.service.model.FeedbackResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackImpl implements FeedbackSI {

    private final FeedbackService feedbackService;

    @Autowired
    FeedbackImpl(@Qualifier("FeedbackService") FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    public ResponseEntity<FeedbackResponse> postComment(@Valid @RequestBody FeedbackRequest feedbackRequest) {
        final FeedbackResponse FeedbackResponse = feedbackService.postComment(feedbackRequest);
        return new ResponseEntity<>(FeedbackResponse, HttpStatus.OK);
    }

}
