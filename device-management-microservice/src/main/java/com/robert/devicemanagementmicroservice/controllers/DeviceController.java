package com.robert.devicemanagementmicroservice.controllers;

import com.robert.devicemanagementmicroservice.dtos.DeviceCreateDTO;
import com.robert.devicemanagementmicroservice.dtos.DeviceDTO;
import com.robert.devicemanagementmicroservice.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) { this.deviceService = deviceService; }

    @PostMapping
    public ResponseEntity<UUID> createDevice( @RequestBody DeviceCreateDTO deviceCreateDTO) {
        UUID deviceId = deviceService.insertDevice(deviceCreateDTO);
        return new ResponseEntity<>(deviceId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        return new ResponseEntity<>(deviceService.getAllDevices(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable("id") UUID deviceId) {
        DeviceDTO deviceDTO = deviceService.getDeviceById(deviceId);
        return new ResponseEntity<>(deviceDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable("id") UUID deviceId, @Valid @RequestBody DeviceCreateDTO deviceCreateDTO) {
        DeviceDTO deviceDTO = deviceService.updateDevice(deviceId, deviceCreateDTO);
        return new ResponseEntity<>(deviceDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable("id") UUID deviceId) {
        deviceService.deleteDevice(deviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
