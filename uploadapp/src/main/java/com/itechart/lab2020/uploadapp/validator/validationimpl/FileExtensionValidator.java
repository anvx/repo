package com.itechart.lab2020.uploadapp.validator.validationimpl;

import com.itechart.lab2020.uploadapp.validator.Validator;

import javax.servlet.http.HttpServletRequest;

public class FileExtensionValidator implements Validator {

    private static final String[] ALLOWED_FILE_EXTENSION = {".jpg", ".png", ".gif"};

    @Override
    public boolean validate(HttpServletRequest req) {

        boolean isAllowed = false;

        String fileName = (String) req.getAttribute("fileName");

        for (String extension : ALLOWED_FILE_EXTENSION) {
            if (fileName.toLowerCase().endsWith(extension)) {
                isAllowed = true;
                break;
            }
        }

        if (isAllowed) {
            return true;
        } else {
            req.setAttribute(ERROR, "File extension is not valid.");
        }
        return false;
    }
}
