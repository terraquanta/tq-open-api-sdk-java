package com.terraqt.open.api.sdk.dto.batch;

import lombok.Data;

import java.util.List;

@Data
public class PaginationBatchDto {
    private List<BatchDto> batches;
    private long total;
}
