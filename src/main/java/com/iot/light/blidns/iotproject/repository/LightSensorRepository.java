package com.iot.light.blidns.iotproject.repository;

import com.iot.light.blidns.iotproject.entity.LightSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightSensorRepository
        extends JpaRepository<LightSensor, Long> {
}
