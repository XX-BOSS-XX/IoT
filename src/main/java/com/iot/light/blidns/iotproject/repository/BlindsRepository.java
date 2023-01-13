package com.iot.light.blidns.iotproject.repository;

import com.iot.light.blidns.iotproject.entity.Blinds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlindsRepository extends JpaRepository<Blinds, Long> {
}
