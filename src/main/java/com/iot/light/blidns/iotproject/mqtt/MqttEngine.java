package com.iot.light.blidns.iotproject.mqtt;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import org.springframework.stereotype.Component;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class MqttEngine {
//    public static void main(String[] args) {

        final String host = "15760f3aa1d047f487194f9bc00d327a.s2.eu.hivemq.cloud";
        final String username = "mqtt-iot";
        final String password = "qwerty12345";

        public void receiveMessage() {
        final Mqtt5BlockingClient client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost(host)
                .serverPort(8883)
                .sslWithDefaultConfig()
                .buildBlocking();

        // connect to HiveMQ Cloud with TLS and username/pw
        client.connectWith()
                .simpleAuth()
                .username(username)
                .password(UTF_8.encode(password))
                .applySimpleAuth()
                .send();

        System.out.println("Connected successfully");

        // subscribe to the topic "my/test/topic"
        client.subscribeWith()
                .topicFilter("my/test/topic")
                .send();

        // set a callback that is called when a message is received (using the async API style)
        client.toAsync().publishes(ALL, publish -> {
            System.out.println("Received message: " +
                    publish.getTopic() + " -> " +
                    UTF_8.decode(publish.getPayload().get()));

            // disconnect the client after a message was received
        });

        // publish a message to the topic "my/test/topic"
        client.publishWith()
                .topic("my/test/topic")
                .payload(UTF_8.encode("40"))
                .send();

        client.toAsync().publishes(ALL, publish -> {
            String message = String.valueOf(UTF_8.decode(publish.getPayload().get()));
            System.out.println(message);
        });
    }

    public String getMessage() {
        return "35";
    }
}
