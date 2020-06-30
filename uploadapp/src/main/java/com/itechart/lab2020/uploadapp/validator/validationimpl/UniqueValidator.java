package com.itechart.lab2020.uploadapp.validator.validationimpl;

import com.itechart.lab2020.uploadapp.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;

public class UniqueValidator implements Validator {
    @Override
    public boolean validate(HttpServletRequest req) {
        String appPath = req.getServletContext().getRealPath("");
        File file = new File(appPath + File.separator + "uploadDirectory");
        String[] list = file.list();
        long count = 0;

        if (list != null) {
            String fileName = (String) req.getAttribute("fileName");
            count = Arrays.stream(list).filter(x -> x.equals(fileName)).count();
        }

        return count == 0;
    }
}
