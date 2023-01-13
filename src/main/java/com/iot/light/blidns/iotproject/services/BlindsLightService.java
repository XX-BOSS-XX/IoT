package com.iot.light.blidns.iotproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlindsLightService {
    private final BlindsService blindsService;
    private final LightService lightService;

    public void roomLogic(Long sensorId) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (LocalDateTime.now().getHour() < 20 && LocalDateTime.now().getHour() > 6) {

            blindsService.updateBlinds(sensorId);
            blindsService.getBlindsBySensorId(1L).stream().forEach(blinds -> {
                var address = blinds.getAddress();
                JsonNode json = objectMapper.createObjectNode()
                        .put("action", "write")
                        .put("address", address)
                        .put("datatype", 5)
                        .put("value", blinds.getAngle());

/*                try {
                    webSocketClient.sendJsonMessage(json);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }*/
            });
        } else {
            blindsService.getBlindsBySensorId(1L).stream().forEach(blinds -> {
                var address = blinds.getAddress();
                JsonNode json = objectMapper.createObjectNode()
                        .put("action", "write")
                        .put("address", address)
                        .put("datatype", 5)
                        .put("value", 10000);
/*                try {
                    webSocketClient.sendJsonMessage(json);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }*/
            });
        }

        lightService.updateLights(1L);
        lightService.getLightsBySensorId(1L).stream().forEach(light -> {
            var address = light.getAddress();
            JsonNode json = objectMapper.createObjectNode()
                    .put("action", "write")
                    .put("address", address)
                    .put("datatype", 5)
                    .put("value", light.getLightLevel());

 /*           try {
                webSocketClient.sendJsonMessage(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }*/
        });
    }

}
