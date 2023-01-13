package com.iot.light.blidns.iotproject.mqtt;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.iot.light.blidns.iotproject.services.BlindsLightService;
import com.iot.light.blidns.iotproject.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.math.NumberUtils;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class MqttEngine {
    private static final String HOST = "15e27783f61d43cdb43210b50caf5683.s1.eu.hivemq.cloud";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "adminadmin";


    @Autowired
    private SensorService sensorService;
    @Autowired
    private BlindsLightService blindsLightService;

    private final Mqtt5BlockingClient client;

    public MqttEngine() {
        try {
            client = MqttClient.builder()
                    .automaticReconnect().applyAutomaticReconnect()
                    .useMqttVersion5()
                    .serverHost(HOST)
                    .serverPort(8883)
                    .sslWithDefaultConfig()
                    .buildBlocking();

            // connect to HiveMQ Cloud with TLS and username/pw
            client.connectWith()
                    .simpleAuth()
                    .username(USERNAME)
                    .password(UTF_8.encode(PASSWORD))
                    .applySimpleAuth()
                    .send();

            System.out.println("Connected successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void receive(String topic) {
        // subscribe to the topic
        client.subscribeWith()
                .topicFilter(topic)
                .send();

        // set a callback that is called when a message is received (using the async API style)
        client.toAsync().publishes(ALL, mqtt5Publish -> {
            var decode = UTF_8.decode(mqtt5Publish.getPayload().get()).toString();
            System.out.println(decode);
            var sensorId = 1L;

            if (NumberUtils.isCreatable(decode)) {
                sensorService.setLight(sensorId, Integer.parseInt(decode));
                blindsLightService.roomLogic(sensorId);
            }
        });
    }

    public void publish(String topic, String message) {
        client.subscribeWith()
                .topicFilter(topic)
                .send();

        client.publishWith()
                .topic(topic)
                .payload(UTF_8.encode(message))
                .send();
    }
}
