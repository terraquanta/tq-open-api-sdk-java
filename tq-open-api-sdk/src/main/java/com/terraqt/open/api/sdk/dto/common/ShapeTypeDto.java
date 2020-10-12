package com.terraqt.open.api.sdk.dto.common;

public enum ShapeTypeDto {
    UNRECOGNIZED(-1),
    ADMINISTRATIVE_REGION(1),
    SHAPE(2)
    ;
    private int id;

    ShapeTypeDto(int id) {
        this.id = id;
    }
}
