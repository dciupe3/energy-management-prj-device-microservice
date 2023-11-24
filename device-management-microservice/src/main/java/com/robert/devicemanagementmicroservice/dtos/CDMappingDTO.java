package com.robert.devicemanagementmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CDMappingDTO {
    private UUID deviceId;
    private UUID userId;
}
