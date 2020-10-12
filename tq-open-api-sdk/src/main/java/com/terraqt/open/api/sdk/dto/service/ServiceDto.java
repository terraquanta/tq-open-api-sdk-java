package com.terraqt.open.api.sdk.dto.service;

import lombok.Data;

@Data
public class ServiceDto {
    private String id;
    private String name;
    private double area;
    private String thumbUrl;
    private long createdAt;
}
