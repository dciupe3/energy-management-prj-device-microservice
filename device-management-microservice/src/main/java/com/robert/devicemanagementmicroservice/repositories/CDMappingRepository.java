package com.robert.devicemanagementmicroservice.repositories;

import com.robert.devicemanagementmicroservice.entities.ClientDeviceMapping;
import com.robert.devicemanagementmicroservice.entities.Device;
import com.robert.devicemanagementmicroservice.services.DeviceService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CDMappingRepository extends JpaRepository<ClientDeviceMapping, UUID> {
    List<ClientDeviceMapping> findAllByUserId(UUID clientId);
    List<ClientDeviceMapping> findAllByDevice(Device device);
}
