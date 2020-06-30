package com.itechart.lab2020.uploadapp.validator;

public class ValidationFactory {

    public static Validator createValidator(ValidatorType type) {

        Validator instance = null;

        try {
            instance = type.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }
}
