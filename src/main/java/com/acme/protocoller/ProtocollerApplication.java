package com.acme.protocoller;

import com.acme.protocoller.mqtt.SubscribeMqtt;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProtocollerApplication {

    public static void main(String[] args) throws MqttException {
        ConfigurableApplicationContext context = SpringApplication.run(ProtocollerApplication.class, args);
        context.getBean(SubscribeMqtt.class).subscribe();
    }

}
