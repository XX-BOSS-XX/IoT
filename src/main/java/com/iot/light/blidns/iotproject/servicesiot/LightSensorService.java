package com.iot.light.blidns.iotproject.servicesiot;

import com.iot.light.blidns.iotproject.entity.LightSensor;
import com.iot.light.blidns.iotproject.mqtt.MqttEngine;
import com.iot.light.blidns.iotproject.repository.LightSensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LightSensorService {
    private final LightSensorRepository sensorService;
    private final MqttEngine mqttEngine;

    public LightSensor saveLightSensor(LightSensor lightSensor) {
        mqttEngine.getMessage();
        System.out.println(mqttEngine.getMessage());
        lightSensor.equals(mqttEngine.getMessage());
        System.out.println(lightSensor);
        return sensorService.save(lightSensor);
    }
}
