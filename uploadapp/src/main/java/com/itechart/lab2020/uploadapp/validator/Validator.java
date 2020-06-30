package com.itechart.lab2020.uploadapp.validator;

import javax.servlet.http.HttpServletRequest;

public interface Validator {

    String ERROR = "errorMessage";

    boolean validate(HttpServletRequest req);
}
