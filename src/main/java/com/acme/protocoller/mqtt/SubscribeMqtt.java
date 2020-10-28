package com.acme.protocoller.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@PropertySource("classpath:application.properties")
@Component
public class SubscribeMqtt {
    private final Logger logger = LoggerFactory.getLogger(SubscribeMqtt.class);

    @Value("${mqtt.broker.uri}")
    private String uri;
    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;

    private final MqttCallbackImpl mqttCallbackImpl;

    public SubscribeMqtt(MqttCallbackImpl mqttCallbackImpl) {
        this.mqttCallbackImpl = mqttCallbackImpl;
    }

    public void subscribe() throws MqttException {
        MqttAsyncClient myClient = new MqttAsyncClient(uri, "protocoller");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(username);
        options.setPassword(password.toCharArray());

        myClient.setCallback(mqttCallbackImpl);

        IMqttToken token = myClient.connect(options);
        token.waitForCompletion();
        logger.info("connected to mqtt-broker: {}", token.isComplete());
        String[] topics = {"hska/avg/temperature1", "hska/avg/temperature2"};
        int[] qoss = {1, 1};
        myClient.subscribe(topics, qoss);
    }
}
