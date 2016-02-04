package com.ebatta.gclp.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ebatta.gclp.persistence.model.ChangeRequest;

@Component
public class ChangeRequestFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangeRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangeRequest changeRequest = (ChangeRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.changeRequest.title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "control", "NotEmpty.changeRequest.control");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customer", "NotEmpty.changeRequest.customer");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "risk", "NotEmpty.changeRequest.risk");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "NotEmpty.changeRequest.state");

        int titleLength = changeRequest.getTitle().length();
        if (titleLength < 5 || titleLength > 150) {
            errors.rejectValue("title", "Size.changeRequest.title");
        }

        int customerStrLength = changeRequest.getCustomer().length();
        if (customerStrLength < 2 || customerStrLength > 100) {
            errors.rejectValue("customer", "Size.changeRequest.customer");
        }
    }

}
