package com.braga.MeasurementsRestApp.utils;

import com.braga.MeasurementsRestApp.models.Measurements;
import com.braga.MeasurementsRestApp.services.MeasurementsService;
import com.braga.MeasurementsRestApp.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class MeasurementsValidator implements Validator {
    private final SensorService sensorService;

    public MeasurementsValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurements.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurements measurements =(Measurements) target;
        if(measurements.getSensor() == null){
            return;
        }
        if(sensorService.findByName(measurements.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor", "Sensor doesnt exist");
        }
    }
}
