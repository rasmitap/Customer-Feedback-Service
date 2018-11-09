package com.target.customer.feedback.service.bl;

import com.target.customer.feedback.service.model.FeedbackRequest;
import com.target.customer.feedback.service.model.FeedbackResponse;

import org.springframework.stereotype.Service;

@Service("FeedbackService")
public class FeedbackService {

    public FeedbackResponse postComment(FeedbackRequest feedbackRequest) {
        final FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.setStatus("comment is posted successfully");
        return feedbackResponse;
    }

}
