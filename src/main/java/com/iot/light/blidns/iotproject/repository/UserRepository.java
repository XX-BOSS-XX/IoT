package com.iot.light.blidns.iotproject.repository;

import com.iot.light.blidns.iotproject.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findUserByUsername(String username);
}
