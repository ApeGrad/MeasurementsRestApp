package org.example.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 8,max = 30,message = "Name should be between 8 and 30 chars")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
