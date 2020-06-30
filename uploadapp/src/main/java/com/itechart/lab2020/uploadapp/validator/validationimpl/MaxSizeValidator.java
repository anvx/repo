package com.itechart.lab2020.uploadapp.validator.validationimpl;

import com.itechart.lab2020.uploadapp.validator.Validator;

import javax.servlet.http.HttpServletRequest;

public class MaxSizeValidator implements Validator {
    @Override
    public boolean validate(HttpServletRequest req) {
        try {
            req.getParts();
            return true;
        } catch (Exception e) {
            req.setAttribute(ERROR, "File takes more than 2Mb. Maximum size is 2Mb");
            return false;
        }
    }
}
