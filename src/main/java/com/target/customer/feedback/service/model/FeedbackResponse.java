package com.target.customer.feedback.service.model;

public class FeedbackResponse {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FeedbackResponse [status=" + status + "]";
    }

}
