package com.terraqt.open.api.sdk.dto.stats;

import lombok.Data;

import java.util.List;

@Data
public class StatsDto {
    private List<StatsItemDto> items;
}
