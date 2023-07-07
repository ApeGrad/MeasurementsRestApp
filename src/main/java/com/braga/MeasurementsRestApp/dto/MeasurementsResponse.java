package com.braga.MeasurementsRestApp.dto;

import com.braga.MeasurementsRestApp.models.Measurements;

import java.util.List;

public class MeasurementsResponse {
    private List<MeasurementsDTO> measurements;

    public MeasurementsResponse(List<MeasurementsDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementsDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementsDTO> measurements) {
        this.measurements = measurements;
    }
}
