package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.dto.MeasurementsDTO;
import org.example.dto.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Consumer {
    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();
        Double minValue = 24.1;
        Double maxValue = 45.1;
        Double value;
        Boolean raining;
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName("Another Sensor");
        for (int i = 0; i < 500; i++) {
            Random random = new Random();
            value= minValue + (maxValue-minValue) * random.nextDouble();
            raining = random.nextBoolean();
            HttpEntity<MeasurementsDTO> request = new HttpEntity<>(new MeasurementsDTO(value,raining,sensorDTO));
            String url = "http://localhost:8080/measurements/add";
            String productCreateResponse = restTemplate
                    .postForObject(url, request, String.class);
            System.out.println(productCreateResponse);

        }

    }
}