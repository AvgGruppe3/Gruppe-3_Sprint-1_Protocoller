package com.acme.protocoller.database;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Protocol")
public class Protocol {

    @EmbeddedId
    @Column(name = "EntryId")
    private EntryId id;

    @Column(name = "Temperature", updatable = false)
    private double temperature;


    public Protocol(EntryId id, double temperature) {
        this.id =id;
        this.temperature = temperature;

    }

    public Protocol() {

    }
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

}