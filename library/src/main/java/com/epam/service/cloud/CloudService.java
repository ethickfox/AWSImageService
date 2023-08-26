package com.epam.service.cloud;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CloudService {

    private final CloudProperties cloudProperties;

    public CloudService(CloudProperties cloudProperties) {
        this.cloudProperties = cloudProperties;
    }

    public Map<String, Object> message() {
        return this.cloudProperties.getZone();
    }
}