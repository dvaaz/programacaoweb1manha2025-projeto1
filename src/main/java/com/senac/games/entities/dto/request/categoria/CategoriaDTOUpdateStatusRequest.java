package com.senac.games.entities.dto.request.categoria;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CategoriaDTOUpdateStatusRequest {
    @NotNull
    @Min(1)
    @Max(2)
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
