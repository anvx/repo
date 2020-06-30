package com.jtmog.alukyanov;

public class ValidationSystem {

    public static boolean validate(String string) throws ValidationFailedException {
        Validator validator = new Factory().createValidator("String");
        if (validator.validate(string)) {
            return true;
        } else {
            throw new ValidationFailedException("Incorrect input: " + string);
        }
    }

    public static boolean validate(Integer integer) throws ValidationFailedException {
        Validator validator = new Factory().createValidator("Integer");
        if (validator.validate(integer)) {
            return true;
        } else {
            throw new ValidationFailedException("Incorrect input: " + integer);
        }
    }
}


