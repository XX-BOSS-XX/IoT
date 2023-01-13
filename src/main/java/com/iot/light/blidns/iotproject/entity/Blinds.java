package com.iot.light.blidns.iotproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Blinds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer height;
    private Integer angle;
    private Integer address;
    @ManyToOne
    @JoinColumn
    private Sensor sensor;

    public Blinds(Integer address, Sensor sensor) {
        this.address = address;
        this.height = 100;
        this.angle = 0;
        this.sensor = sensor;
    }

}
