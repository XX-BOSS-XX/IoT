package com.iot.light.blidns.iotproject.servicesiot;

import com.iot.light.blidns.iotproject.entity.Room;
import com.iot.light.blidns.iotproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomService {
    private final RoomRepository roomRepository;

    public Room saveLight(Room room) {
        return roomRepository.save(room);
    }
}
