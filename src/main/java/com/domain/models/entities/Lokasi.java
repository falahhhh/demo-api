package com.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lokasi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lokasi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_lokasi", nullable = false)
    private String namaLokasi;

    private String negara;
    private String provinsi;
    private String kota;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "lokasi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProyekLokasi> proyekLokasi;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public void validate() {
        if (StringUtils.isBlank(namaLokasi)) {
            throw new IllegalArgumentException("Nama lokasi tidak boleh kosong.");
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
