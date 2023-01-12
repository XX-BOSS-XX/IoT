package com.iot.light.blidns.iotproject.repository;

import com.iot.light.blidns.iotproject.entity.Blind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlindRepository
        extends JpaRepository<Blind, Long> {
}
