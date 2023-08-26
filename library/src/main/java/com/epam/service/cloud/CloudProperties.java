package com.epam.service.cloud;

import org.springframework.stereotype.Component;
import  software.amazon.awssdk.regions.internal.util.EC2MetadataUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CloudProperties {
    public Map<String, Object> getZone() {
        EC2MetadataUtils.InstanceInfo info = EC2MetadataUtils.getInstanceInfo();

        Map<String, Object> metadata = new LinkedHashMap<>();
        metadata.put("privateIp", info.getPrivateIp());
        metadata.put("availabilityZone", info.getAvailabilityZone());
        metadata.put("instanceId", info.getInstanceId());
        metadata.put("instanceType", info.getInstanceType());
        metadata.put("accountId", info.getAccountId());
        metadata.put("amiId", info.getImageId());
        metadata.put("region", info.getRegion());
        return metadata;
    }
}