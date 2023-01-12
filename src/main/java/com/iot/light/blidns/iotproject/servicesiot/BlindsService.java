package com.iot.light.blidns.iotproject.servicesiot;

import com.iot.light.blidns.iotproject.entity.Blind;
import com.iot.light.blidns.iotproject.repository.BlindRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BlindsService {
    private final BlindRepository blindRepository;

    public Blind saveWork(Blind blinds) {
        return blindRepository.save(blinds);
    }
}
