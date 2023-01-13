package com.iot.light.blidns.iotproject.services;

import com.iot.light.blidns.iotproject.entity.Blinds;
import com.iot.light.blidns.iotproject.repository.BlindsRepository;
import com.iot.light.blidns.iotproject.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlindsService {

    private final BlindsRepository blindsRepository;
    private final SensorRepository sensorRepository;

    public Collection<Blinds> getBlindsBySensorId(Long sensorId) {
        return blindsRepository.findAll().stream()
                .filter(blinds -> blinds.getSensor().getId().equals(sensorId))
                .collect(Collectors.toList());
    }

    public void updateBlinds(Long sensorId) {
        var sensor = sensorRepository.findById(sensorId).orElseThrow();
        var blinds = getBlindsBySensorId(sensorId);

        if (sensor.getLightValue() <= 300) {
            blinds.stream().forEach(b -> {
                b.setAngle(100);
                b.setHeight(100);
            });
        } else if (sensor.getLightValue() <= 500) {
            blinds.stream().forEach(b -> {
                b.setAngle(0);
                b.setHeight(100);
            });
        } else {
            blinds.stream().forEach(b -> {
                b.setAngle(200);
                b.setHeight(100);
            });
        }
        blindsRepository.saveAll(blinds);
    }

    public void setSensor(Integer angel, Long sensorId) {
        var sensor = sensorRepository.findById(sensorId).orElseThrow();
        sensor.setLightValue(angel);
    }
}
