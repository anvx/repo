package com.jtmog.alukyanov;

public class Factory {
    public Validator createValidator(String nameOfValidator) {
        if (nameOfValidator.equals("Integer")) {
            return new IntegerValidator();
        }
        if (nameOfValidator.equals("String")) {
            return new StringValidator();
        }
        throw new IllegalArgumentException();
    }
}
