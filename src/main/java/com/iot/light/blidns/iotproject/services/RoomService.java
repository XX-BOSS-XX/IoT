package com.iot.light.blidns.iotproject.services;

import com.iot.light.blidns.iotproject.entity.Room;
import com.iot.light.blidns.iotproject.repository.RoomRepository;
import com.iot.light.blidns.iotproject.repository.RoomRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomService implements RoomRepository {
    private final RoomRepositoryService roomRepositoryService;

    @Override
    public Room saveLight(Room room) {
        return roomRepositoryService.save(room);
    }
}
