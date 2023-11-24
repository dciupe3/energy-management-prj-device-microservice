package com.robert.devicemanagementmicroservice.dtos;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeviceDTO {
    @NonNull
    private UUID id;
    @NonNull
    private String description;
    @NonNull
    private String address;
    @NonNull
    private Integer max_hourly_consumption;

    public DeviceDTO() {

    }

    public DeviceDTO(@NonNull UUID id, @NonNull String description, @NonNull String address, @NonNull Integer maxHourlyConsumption) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.max_hourly_consumption = maxHourlyConsumption;
    }
}
