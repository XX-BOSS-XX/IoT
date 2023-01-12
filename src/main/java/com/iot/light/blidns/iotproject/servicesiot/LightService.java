package com.iot.light.blidns.iotproject.servicesiot;

import com.iot.light.blidns.iotproject.entity.Light;
import com.iot.light.blidns.iotproject.repository.LightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LightService {
    private final LightRepository bulbsRepository;

    public Light saveBulbs(Light bulbs) {
        return null;
    }
}
