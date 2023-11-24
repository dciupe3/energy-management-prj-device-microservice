package com.robert.devicemanagementmicroservice.services;

import com.robert.devicemanagementmicroservice.controllers.exceptions.model.ResourceNotFoundException;
import com.robert.devicemanagementmicroservice.dtos.DeviceCreateDTO;
import com.robert.devicemanagementmicroservice.dtos.DeviceDTO;
import com.robert.devicemanagementmicroservice.dtos.builders.DeviceBuilder;
import com.robert.devicemanagementmicroservice.entities.ClientDeviceMapping;
import com.robert.devicemanagementmicroservice.entities.Device;
import com.robert.devicemanagementmicroservice.repositories.CDMappingRepository;
import com.robert.devicemanagementmicroservice.repositories.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService
{
    private final CDMappingRepository cdMappingRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, CDMappingRepository cdMappingRepository) {
        this.deviceRepository = deviceRepository;
        this.cdMappingRepository = cdMappingRepository;
    }

    public UUID insertDevice(DeviceCreateDTO deviceCreateDTO) {
        Device device = DeviceBuilder.toEntity(deviceCreateDTO);
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in db", device.getId());
        return device.getId();
    }

    public List<DeviceDTO> getAllDevices() {
        return deviceRepository.findAll().stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO getDeviceById(UUID deviceId) {
        Optional<Device> prosumerOptional = deviceRepository.findById(deviceId);
        if(prosumerOptional.isEmpty()) {
           LOGGER.error("Device with id {} was not found in db", deviceId);
           throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceId);
        }
        return DeviceBuilder.toDeviceDTO(prosumerOptional.get());

    }


    public DeviceDTO updateDevice(UUID deviceId, DeviceCreateDTO deviceCreateDTO) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException(Device.class.getSimpleName() + "with id: " + deviceId));

        //if null?
        device.setAddress(deviceCreateDTO.getAddress());
        device.setDescription(deviceCreateDTO.getDescription());
        device.setMax_hourly_consumption(deviceCreateDTO.getMax_hourly_consumption());

        LOGGER.debug("Device with id {} was updated in db", device.getId());

        return DeviceBuilder.toDeviceDTO(deviceRepository.save(device));
    }

    public void deleteDevice(UUID deviceId) {

        List<ClientDeviceMapping> mappings = cdMappingRepository.findAllByDevice(deviceRepository.getReferenceById(deviceId));

        cdMappingRepository.deleteAll(mappings);


//        System.out.println(deviceRepository.existsById(deviceId) + "!!!!!!!");
        if(!deviceRepository.existsById(deviceId)) {
            LOGGER.error("Device with id {} was not found in db", deviceId);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceId);
        }
        deviceRepository.deleteById(deviceId);
    }


}
