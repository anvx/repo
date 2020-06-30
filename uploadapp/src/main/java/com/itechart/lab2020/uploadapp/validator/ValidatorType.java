package com.itechart.lab2020.uploadapp.validator;

import com.itechart.lab2020.uploadapp.validator.validationimpl.FileExtensionValidator;
import com.itechart.lab2020.uploadapp.validator.validationimpl.MaxSizeValidator;
import com.itechart.lab2020.uploadapp.validator.validationimpl.UniqueValidator;

import java.lang.reflect.InvocationTargetException;

public enum ValidatorType {
    FILE_EXTENSION(FileExtensionValidator.class),
    FILE_MAX_SIZE(MaxSizeValidator.class),
    FILE_UNIQUE(UniqueValidator.class);

    private Class<? extends Validator> className;

    ValidatorType(Class<? extends Validator> className) {
        this.className = className;
    }

    public Validator getInstance()
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {

        return className.getDeclaredConstructor().newInstance();
    }
}