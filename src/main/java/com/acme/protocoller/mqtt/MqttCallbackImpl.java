package com.acme.protocoller.mqtt;

import com.acme.protocoller.database.Protocol;
import com.acme.Sensor;
import com.acme.protocoller.database.ProtocolRepository;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MqttCallbackImpl implements MqttCallback {
    private final Logger logger = LoggerFactory.getLogger(MqttCallbackImpl.class);

    private final ProtocolRepository protocolRepository;

    @Autowired
    public MqttCallbackImpl(ProtocolRepository protocolRepository) {


        this.protocolRepository = protocolRepository;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        //Auto-generated method stub
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //Auto-generated method stub
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage, Protocol protocol) {
        String temperatureString = new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);
        logger.info(topic + ": " + temperatureString);
        try {
            double temperatureValue = Double.parseDouble(temperatureString);
            Sensor sensor = Sensor.getSensorByMqttTopic(topic);
            sensor.temperature = temperatureValue;

            if(System.currentTimeMillis() >= (sensor.timestampEmail + 60000)) {
                if (temperatureValue > 25) {
                    protocol.setTopic(topic);
                    protocol.setTemperature(temperatureValue);
                    protocol.setTime(sensor.timestampEmail);
                    protocolRepository.save(protocol);
                }

            }
        }catch (NumberFormatException e){
            logger.info("sent message is not a number: {}", mqttMessage );
        }

    }
}
