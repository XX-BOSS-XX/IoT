package com.iot.light.blidns.iotproject.classcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iot.light.blidns.iotproject.repository.SensorRepository;
import com.iot.light.blidns.iotproject.services.LightService;
import com.iot.light.blidns.iotproject.websocket.WebSocketClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/click/light")
@RequiredArgsConstructor
public class LightController {
    private final LightService lightService;
    private final SensorRepository sensorRepository;

    private final WebSocketClient webSocketClient;

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/on")
    public void onLight() {
        lightService.getLightsBySensorId(1L).stream().forEach(light -> {
            light.setLightLevel(1000);
            try {
                webSocketClient.sendJsonMessage(light.getAddress(), light.getLightLevel());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
//            blindsRepository.save(blinds);
        });
//        lightService.updateLights(2L);
        System.out.println("on light");
    }

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/medium")
    public void onMedium() {
        lightService.getLightsBySensorId(1L).stream().forEach(light -> {
            light.setLightLevel(50);
            try {
                webSocketClient.sendJsonMessage(light.getAddress(), light.getLightLevel());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("medium light");
    }

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/off")
    public void offLight() {
        lightService.getLightsBySensorId(1L).stream().forEach(light -> {
            light.setLightLevel(0);
            try {
                webSocketClient.sendJsonMessage(light.getAddress(), light.getLightLevel());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
//            blindsRepository.save(blinds);
        });
        System.out.println("off light");
    }

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/value/{lightLevel}")
    public void ruleLightLevel(@PathVariable Integer lightLevel) {
        lightService.getLightsBySensorId(1L).stream().forEach(light -> {
            light.setLightLevel(lightLevel);
            try {
                webSocketClient.sendJsonMessage(light.getAddress(), light.getLightLevel());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
//            blindsRepository.save(blinds);
        });
        System.out.println("set light level");
    }
}
