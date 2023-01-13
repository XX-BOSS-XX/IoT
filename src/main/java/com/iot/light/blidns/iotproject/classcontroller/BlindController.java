package com.iot.light.blidns.iotproject.classcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.light.blidns.iotproject.repository.BlindsRepository;
import com.iot.light.blidns.iotproject.repository.SensorRepository;
import com.iot.light.blidns.iotproject.services.BlindsService;
import com.iot.light.blidns.iotproject.websocket.WebSocketClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.runtime.ObjectMethods;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/click/blinds")
@RequiredArgsConstructor
public class BlindController {
    private final SensorRepository sensorRepository;
    private final BlindsService blindsService;
    private final WebSocketClient webSocketClient;
//    private final BlindsRepository blindsRepository;

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all/open")
    public void openBlind() {
        blindsService.getBlindsBySensorId(1L).stream().forEach(blinds -> {
            blinds.setAngle(0);
            try {
                webSocketClient.sendJsonMessage(blinds.getAddress(), blinds.getAngle());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
//            blindsRepository.save(blinds);
        });
//        blindsService.updateBlinds(2L);
        System.out.println("open blind");
    }

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/open/{id}")
    public void openBlindId(@PathVariable Integer id) {
        blindsService.getBlindsBySensorId(1L).stream().forEach(blinds -> {
            blinds.setAngle(0);
            try {
                webSocketClient.sendJsonMessage(blinds.getAddress(), blinds.getAngle());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
//            blindsRepository.save(blinds);
        });
        System.out.println("open" + id + "blind");
    }

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/close{id}")
    public void closeBlindId(@PathVariable Integer id) {
        blindsService.updateBlinds(2L);
        System.out.println("close" + id + "blind");
    }

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all/close")
    public void closeBlind() throws JsonProcessingException {

//        blindsService.updateBlinds(3L);
        blindsService.getBlindsBySensorId(1L).stream().forEach(blinds -> {
            blinds.setAngle(10000);
            try {
                webSocketClient.sendJsonMessage(blinds.getAddress(), blinds.getAngle());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
//            blindsRepository.save(blinds);
        });


        System.out.println("close blind");
    }

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/value/{angle}")
    public void closeBlind(@PathVariable Integer angle) {
        var sen = sensorRepository.findById(4L).orElseThrow();
        sen.setLightValue(angle);
        sensorRepository.save(sen);
        blindsService.updateBlinds(4L);
        System.out.println("move blind");
    }
}
