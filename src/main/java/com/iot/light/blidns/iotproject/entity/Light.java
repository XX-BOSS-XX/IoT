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
public class Light {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer address;
  private Integer lightLevel;
  @ManyToOne
  @JoinColumn
  private Sensor sensor;

  public Light(Integer address, Sensor sensor) {
    this.address = address;
    this.lightLevel = 0;
    this.sensor = sensor;
  }
}
