package com.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LokasiData {

    @NotEmpty(message = "nama lokasi is required")
    @JsonProperty("nama_lokasi")
    private String namaLokasi;

    @NotEmpty(message = "negara is required")
    private String negara;

    @NotEmpty(message = "provinsi is required")
    private String provinsi;

    @NotEmpty(message = "kota is required")
    private String kota;
}
