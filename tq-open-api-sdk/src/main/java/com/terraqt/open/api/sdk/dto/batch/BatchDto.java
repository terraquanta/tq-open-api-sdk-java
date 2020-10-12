package com.terraqt.open.api.sdk.dto.batch;

import lombok.Data;

@Data
public class BatchDto {
    private String batchId;
    private String batchName;
    private long dataTime;
    private long publishTime;
}
