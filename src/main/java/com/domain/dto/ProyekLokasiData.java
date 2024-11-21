package com.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProyekLokasiData {

    @NotNull(message = "Proyek ID is required")
    private Long proyekId;

    @NotNull(message = "Lokasi ID is required")
    private Long lokasiId;
}
