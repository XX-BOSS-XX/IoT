package com.iot.light.blidns.iotproject.services;

import com.iot.light.blidns.iotproject.entity.Blind;
import com.iot.light.blidns.iotproject.repository.BlindRepository;
import com.iot.light.blidns.iotproject.repository.BlindRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BlindsService implements BlindRepository {
    private final BlindRepositoryService blindRepository;

    @Override
    public Blind saveWork(Blind blinds) {
        return blindRepository.save(blinds);
    }
}
