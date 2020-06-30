package com.jtmog.alukyanov;

class StringValidator implements Validator<String> {
    @Override
    public boolean validate(String string) {
        return string.matches("[A-Z].*");
    }
}
