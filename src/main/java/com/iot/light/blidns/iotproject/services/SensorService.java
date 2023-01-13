package com.iot.light.blidns.iotproject.services;

import com.iot.light.blidns.iotproject.entity.Sensor;
import com.iot.light.blidns.iotproject.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorService {

  private final SensorRepository sensorRepository;

  public void setLight(Long id, Integer lightValue) {
    Sensor sensor = sensorRepository.findById(id).orElseThrow();
    sensor.setLightValue(lightValue);
    sensorRepository.save(sensor);
  }
}
