package com.braga.MeasurementsRestApp.repositories;

import com.braga.MeasurementsRestApp.models.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

}
