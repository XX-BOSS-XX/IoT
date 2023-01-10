package com.iot.light.blidns.iotproject.repository;

import com.iot.light.blidns.iotproject.entity.Blind;
import org.springframework.stereotype.Repository;

@Repository
public interface BlindRepository {
    Blind saveWork(Blind blinds);
}
