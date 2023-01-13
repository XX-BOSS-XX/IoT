package com.iot.light.blidns.iotproject.repository;

import com.iot.light.blidns.iotproject.entity.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightRepository extends JpaRepository<Light, Long> {
}
