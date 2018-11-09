package com.target.customer.feedback.controller.impl;

import com.target.customer.feedback.service.model.FeedbackRequest;
import com.target.customer.feedback.service.model.FeedbackResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface FeedbackSI {
    @PostMapping(value = "/customer/feedback", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedbackResponse> postComment(@RequestBody FeedbackRequest feedbackRequest);
}
