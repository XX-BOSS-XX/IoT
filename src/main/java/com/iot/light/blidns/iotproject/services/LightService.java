package com.iot.light.blidns.iotproject.services;

import com.iot.light.blidns.iotproject.entity.Light;
import com.iot.light.blidns.iotproject.repository.LightRepository;
import com.iot.light.blidns.iotproject.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LightService {

    private final LightRepository lightRepository;
    private final SensorRepository sensorRepository;


    public Collection<Light> getLightsBySensorId(Long sensorId) {
        return lightRepository.findAll().stream()
                .filter(lights -> lights.getSensor().getId().equals(sensorId))
                .collect(Collectors.toList());
    }

    public void updateLights(Long sensorId) {
        var sensor = sensorRepository.findById(sensorId).orElseThrow();
        var lights = getLightsBySensorId(sensorId);


        if (sensor.getLightValue() <= 300) {
            lights.stream().forEach(l -> l.setLightLevel(0));
        } else if (sensor.getLightValue() <= 500) {
            lights.stream().forEach(l -> l.setLightLevel(60));
        } else {
            lights.stream().forEach(l -> l.setLightLevel(100));
        }

        lightRepository.saveAll(lights);
    }
}
