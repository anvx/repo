package com.jtmog.alukyanov;

class IntegerValidator implements Validator<Integer> {
    @Override
    public boolean validate(Integer value) {
        if ((value < 1) || (value > 10)) {
            return false;
        }
        return true;
    }
}
