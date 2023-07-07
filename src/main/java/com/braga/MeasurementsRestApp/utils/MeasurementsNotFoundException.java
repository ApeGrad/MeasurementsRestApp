package com.braga.MeasurementsRestApp.utils;

public class MeasurementsNotFoundException extends RuntimeException {
    public MeasurementsNotFoundException(String message) {
        super(message);
    }
}
