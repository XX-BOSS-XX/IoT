package com.iot.light.blidns.iotproject.repository;

import com.iot.light.blidns.iotproject.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
