package com.acme.protocoller.database;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class EntryId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "topic")
    private String topic;

    @Column(name = "time")
    private LocalDateTime time;

    public EntryId (String topic, LocalDateTime time) {
        this.topic = topic;
        this.time = time;
    }

    public EntryId() {}

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
