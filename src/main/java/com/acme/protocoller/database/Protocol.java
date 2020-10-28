package com.acme.protocoller.database;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Protocol")
public class Protocol {

    @Id
    @Column(name = "Topic", updatable = false)
    private String topic;

    @Column(name = "Temperature", updatable = false)
    private double temperature;

    @Id
    @Column(name = "Date", updatable = false)
    private LocalDateTime time;


    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}