package com.robert.devicemanagementmicroservice.dtos.builders;


import com.robert.devicemanagementmicroservice.dtos.DeviceCreateDTO;
import com.robert.devicemanagementmicroservice.dtos.DeviceDTO;
import com.robert.devicemanagementmicroservice.entities.Device;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceBuilder {

    private DeviceBuilder() {

    }

    public static DeviceDTO toDeviceDTO(Device device) {
        return new DeviceDTO(device.getId(), device.getDescription(), device.getAddress(), device.getMax_hourly_consumption());
    }

    public static Device toEntity(DeviceCreateDTO deviceCreateDTO) {
        return new Device(deviceCreateDTO.getDescription(),
                deviceCreateDTO.getAddress(),
                deviceCreateDTO.getMax_hourly_consumption());
    }

}
