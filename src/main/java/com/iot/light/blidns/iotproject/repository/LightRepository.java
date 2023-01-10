package com.iot.light.blidns.iotproject.repository;

import com.iot.light.blidns.iotproject.entity.Light;
import org.springframework.stereotype.Repository;

@Repository
public interface LightRepository {
    Light saveBulbs(Light bulbs);
}
