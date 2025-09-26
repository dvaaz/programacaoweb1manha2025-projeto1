package com.senac.games.entities.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PatrocinadorDTOUpdateStatusRequest {
    @NotNull
    @NotEmpty
    @Min(1)
    @Max(2)
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
