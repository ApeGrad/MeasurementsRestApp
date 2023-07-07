package com.braga.MeasurementsRestApp.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorUtil {

    public static void returnErrorToClient(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();

        List<FieldError> errorMsg = bindingResult.getFieldErrors();
        for(FieldError error: errorMsg) {
            stringBuilder.append(error.getField()).append(" - ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(" : ");
        }
        throw new MeasurementsNotFoundException(stringBuilder.toString());
    }
}
