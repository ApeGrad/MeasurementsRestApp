package com.braga.MeasurementsRestApp.controllers;

import com.braga.MeasurementsRestApp.dto.MeasurementsDTO;
import com.braga.MeasurementsRestApp.dto.MeasurementsResponse;
import com.braga.MeasurementsRestApp.models.Measurements;
import com.braga.MeasurementsRestApp.services.MeasurementsService;
import com.braga.MeasurementsRestApp.utils.ErrorUtil;
import com.braga.MeasurementsRestApp.utils.MeasurementsError;
import com.braga.MeasurementsRestApp.utils.MeasurementsNotFoundException;
import com.braga.MeasurementsRestApp.utils.MeasurementsValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final MeasurementsValidator validator;
    private final ModelMapper modelMapper;
    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, MeasurementsValidator validator, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementsDTO measurementDTO,
                                          BindingResult bindingResult) {
        Measurements measurementToAdd = convertToMeasurements(measurementDTO);

        validator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
           ErrorUtil.returnErrorToClient(bindingResult);
        }

        measurementsService.addMeasurements(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping()
    public MeasurementsResponse getMeasurements(){
        return new MeasurementsResponse(measurementsService.findAll().stream().map(this::convertToMeasurementsDTO)
                .collect(Collectors.toList()));
    }

    private Measurements convertToMeasurements(MeasurementsDTO measurementsDTO) {
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    private MeasurementsDTO convertToMeasurementsDTO(Measurements measurements) {
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }
    @ExceptionHandler
    private ResponseEntity<MeasurementsError> handleException(MeasurementsNotFoundException e) {
        MeasurementsError response = new MeasurementsError(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
