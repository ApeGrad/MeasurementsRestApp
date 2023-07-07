package com.braga.MeasurementsRestApp.services;

import com.braga.MeasurementsRestApp.models.Measurements;
import com.braga.MeasurementsRestApp.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }
    public List<Measurements> findAll(){
       return measurementsRepository.findAll();
    }
    @Transactional
    public void addMeasurements(Measurements measurements) {
        enrichMeasurements(measurements);
        measurementsRepository.save(measurements);
    }

    public void enrichMeasurements(Measurements measurements){
        measurements.setSensor(sensorService.findByName(measurements.getSensor().getName()).get());
        measurements.setMeasurementDateTime(LocalDateTime.now());
    }

}
