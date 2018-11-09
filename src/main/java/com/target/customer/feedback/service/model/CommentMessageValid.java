package com.target.customer.feedback.service.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.target.customer.feedback.service.model.FeedbackRequest;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommentMessageValidator.class)
public @interface CommentMessageValid {

	    String message() default "{com.target.customer.feedback.service.model.FeedbackRequest.message}";
	    Class<?>[] groups() default {};
	    Class<? extends FeedbackRequest>[] payload() default {};
}
