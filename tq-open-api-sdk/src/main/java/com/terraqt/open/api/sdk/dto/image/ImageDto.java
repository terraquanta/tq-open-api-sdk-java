package com.terraqt.open.api.sdk.dto.image;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDto {
    private String tiffUrl;
    private String geometry;
    private LocalDateTime time;
    private LocalDateTime createdAt;
}
