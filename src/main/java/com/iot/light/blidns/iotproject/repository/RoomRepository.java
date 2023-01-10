package com.iot.light.blidns.iotproject.repository;

import com.iot.light.blidns.iotproject.entity.Room;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository {
    Room saveLight(Room room);
}
