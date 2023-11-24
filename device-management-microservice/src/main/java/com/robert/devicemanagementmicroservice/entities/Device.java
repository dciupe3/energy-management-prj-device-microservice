package com.robert.devicemanagementmicroservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Setter
@Getter
public class Device {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "max_hourly_consumption", nullable = false)
    private Integer max_hourly_consumption;

    public Device() {
    }

    public Device(String description, String address, Integer maxHourlyConsumption) {
        this.description = description;
        this.address = address;
        this.max_hourly_consumption = maxHourlyConsumption;
    }
}
