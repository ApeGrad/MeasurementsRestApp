package com.braga.MeasurementsRestApp.controllers;

import com.braga.MeasurementsRestApp.dto.SensorDTO;
import com.braga.MeasurementsRestApp.models.Sensor;
import com.braga.MeasurementsRestApp.services.MeasurementsService;
import com.braga.MeasurementsRestApp.services.SensorService;
import com.braga.MeasurementsRestApp.utils.ErrorUtil;
import com.braga.MeasurementsRestApp.utils.MeasurementsError;
import com.braga.MeasurementsRestApp.utils.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;
    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor,bindingResult);
        if(bindingResult.hasErrors()) {
            ErrorUtil.returnErrorToClient(bindingResult);
        }
        sensorService.register(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }




    public Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
    @ExceptionHandler
    public ResponseEntity<MeasurementsError> measurementsErrorHandler(MeasurementsError e){
        MeasurementsError response = new MeasurementsError(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
