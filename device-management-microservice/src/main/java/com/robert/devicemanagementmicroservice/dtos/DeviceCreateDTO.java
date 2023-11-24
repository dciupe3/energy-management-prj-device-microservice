package com.robert.devicemanagementmicroservice.dtos;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class DeviceCreateDTO {
    @NonNull
    private String description;
    @NonNull
    private String address;
    @NonNull
    private Integer max_hourly_consumption;

    public DeviceCreateDTO(){

    }

    public DeviceCreateDTO(@NonNull String description, @NonNull String address, @NonNull Integer maxHourlyConsumption) {
        this.description = description;
        this.address = address;
        this.max_hourly_consumption = maxHourlyConsumption;
    }
}
