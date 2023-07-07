package com.braga.MeasurementsRestApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class MeasurementsDTO {
    @NotNull
    @Min(value = -100)
    @Max(value = 100)
    private Double value;

    @NotNull
    private Boolean raining;
    @NotNull
    private SensorDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensorDTO() {
        return sensor;
    }

    public void setSensorDTO(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
