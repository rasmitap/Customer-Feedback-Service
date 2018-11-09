package com.target.customer.feedback.service.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class CommentMessageValidator implements ConstraintValidator<CommentMessageValid, String> {

    CommentMessageValidator() {

    }

    @Override
    public boolean isValid(String feedbackRequest, ConstraintValidatorContext context) {
        final boolean valid = true;
        final List<String> feedbackDataList = Arrays.asList(feedbackRequest.split(" ")).stream().map(String::toUpperCase)
                .collect(Collectors.toList());
        ;
        if (feedbackDataList.contains("BAD")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("negative feedback is not appreciated").addConstraintViolation();
            return false;
        }
        return valid;
    }

}
