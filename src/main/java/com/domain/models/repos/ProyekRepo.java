package com.domain.models.repos;

import com.domain.models.entities.Proyek;

import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ProyekRepo extends CrudRepository<Proyek, Long>{

    @Query("Select p From Proyek p Where p.namaProyek = :namaProyek")
    public Proyek findProyekByName(@PathParam("namaProyek") String namaProyek);

    @Query("Select p From Proyek p Where p.namaProyek Like :namaProyek")
    public List<Proyek> findProyekByNameLike(@PathParam("namaProyek") String namaProyek);
}