package com.target.customer.feedback.service.model;

public class FeedbackRequest {

    private String mailId;

    @CommentMessageValid
    private String message;

    public FeedbackRequest() {

    }

    public FeedbackRequest(String message) {
        this.message = message;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
