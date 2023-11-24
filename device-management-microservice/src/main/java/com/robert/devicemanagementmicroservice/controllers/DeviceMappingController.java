package com.robert.devicemanagementmicroservice.controllers;

import com.robert.devicemanagementmicroservice.dtos.CDMappingDTO;
import com.robert.devicemanagementmicroservice.dtos.DeviceDTO;
import com.robert.devicemanagementmicroservice.entities.Device;
import com.robert.devicemanagementmicroservice.services.CDMappingService;
import com.robert.devicemanagementmicroservice.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/device-mappings")
@CrossOrigin
public class DeviceMappingController {

    @Autowired
    private final CDMappingService cdMappingService;

    public DeviceMappingController(CDMappingService cdMappingService){
        this.cdMappingService = cdMappingService;
    }

    @PostMapping("/")
    public ResponseEntity<UUID> createDeviceMapping(@RequestBody CDMappingDTO deviceMappingDTO) {
        UUID id = cdMappingService.insertMapping(deviceMappingDTO);
        return ResponseEntity.ok(id);
    }

    @GetMapping("devices/client/{id}")
    public ResponseEntity<List<DeviceDTO>> getClientDevices (@PathVariable("id") UUID id) {

        return ResponseEntity.ok(cdMappingService.getDevicesByClientId(id));

    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDeviceMapping(@PathVariable UUID id) {
//        deviceService.deleteDeviceMapping(id);
//        return ResponseEntity.ok().build();
//    }
}