package com.acme.protocoller.mqtt;

import com.acme.Sensor;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@PropertySource("classpath:mqtt.properties")
@Component
public class SubscribeMqtt {
    private final Logger logger = LoggerFactory.getLogger(SubscribeMqtt.class);

    @Value("${mqtt.broker.uri}")
    private String uri;

    private final MqttCallbackImpl mqttCallbackImpl;

    public SubscribeMqtt(MqttCallbackImpl mqttCallbackImpl) {
        this.mqttCallbackImpl = mqttCallbackImpl;
    }

    public void subscribe() throws MqttException {
        MqttAsyncClient myClient = new MqttAsyncClient(uri, UUID.randomUUID().toString());

        myClient.setCallback(mqttCallbackImpl);

        IMqttToken token = myClient.connect();
        token.waitForCompletion();
        logger.info("connected to mqtt-broker: {}", token.isComplete());
        String[] topics = {Sensor.SENSOR_1.mqttTopic, Sensor.SENSOR_2.mqttTopic};
        int[] qoss = {1, 1};
        myClient.subscribe(topics, qoss);
    }
}
