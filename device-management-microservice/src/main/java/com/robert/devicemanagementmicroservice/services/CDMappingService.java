package com.robert.devicemanagementmicroservice.services;

import com.robert.devicemanagementmicroservice.dtos.CDMappingDTO;
import com.robert.devicemanagementmicroservice.dtos.DeviceDTO;
import com.robert.devicemanagementmicroservice.entities.ClientDeviceMapping;
import com.robert.devicemanagementmicroservice.entities.Device;
import com.robert.devicemanagementmicroservice.repositories.CDMappingRepository;
import com.robert.devicemanagementmicroservice.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CDMappingService {
    private final CDMappingRepository cdMappingRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    public CDMappingService(CDMappingRepository cDMappingRepository) {
        this.cdMappingRepository = cDMappingRepository;
    }

    public UUID insertMapping(CDMappingDTO dto) {
        ClientDeviceMapping deviceMapping = new ClientDeviceMapping();
        Device device = deviceRepository.getReferenceById(dto.getDeviceId());
        deviceMapping.setDevice(device);
        deviceMapping.setUserId(dto.getUserId());

        ClientDeviceMapping clientDeviceMapping = cdMappingRepository.save(deviceMapping);
        return clientDeviceMapping.getId();
    }


    public List<DeviceDTO> getDevicesByClientId(UUID clientId) {
        List<ClientDeviceMapping> mappings = cdMappingRepository.findAllByUserId(clientId);

        return mappings.stream().map(mapping -> {
            Device device = mapping.getDevice();
            return new DeviceDTO(device.getId(), device.getDescription(), device.getAddress(), device.getMax_hourly_consumption());
        }).collect(Collectors.toList());
    }
}
