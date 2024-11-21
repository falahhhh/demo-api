package com.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.domain.dto.ProyekLokasiData;
import com.domain.dto.ResponseData;
import com.domain.models.entities.ProyekLokasi;
import com.domain.services.LokasiService;
import com.domain.services.ProyekService;
import com.domain.services.ProyekLokasiService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proyek-lokasi")
public class ProyekLokasiController {

    @Autowired
    private ProyekLokasiService proyekLokasiService;

    @Autowired
    private ProyekService proyekService;

    @Autowired
    private LokasiService lokasiService;

    // Endpoint untuk menambahkan relasi Proyek dan Lokasi
    @PostMapping
    public ResponseEntity<ResponseData<ProyekLokasi>> create(
            @Valid @RequestBody ProyekLokasiData proyekLokasiData,
            Errors errors) {

        ResponseData<ProyekLokasi> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        ProyekLokasi proyekLokasi = new ProyekLokasi();
        proyekLokasi.setProyek(proyekService.findOne(proyekLokasiData.getProyekId()));
        proyekLokasi.setLokasi(lokasiService.findOne(proyekLokasiData.getLokasiId()));

        responseData.setStatus(true);
        responseData.setPayload(proyekLokasiService.save(proyekLokasi));
        return ResponseEntity.ok(responseData);
    }

    // Endpoint untuk melihat semua relasi Proyek dan Lokasi
    @GetMapping
    public ResponseEntity<Iterable<ProyekLokasi>> findAll() {
        return ResponseEntity.ok(proyekLokasiService.findAll());
    }

    // Endpoint untuk melihat relasi berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<ProyekLokasi> findOne(@PathVariable("id") Long id) {
        ProyekLokasi proyekLokasi = proyekLokasiService.findOne(id);
        if (proyekLokasi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(proyekLokasi);
    }

    // Endpoint untuk memperbarui relasi Proyek dan Lokasi
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<ProyekLokasi>> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody ProyekLokasiData proyekLokasiData,
            Errors errors) {

        ResponseData<ProyekLokasi> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        ProyekLokasi proyekLokasi = proyekLokasiService.findOne(id);
        if (proyekLokasi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        proyekLokasi.setProyek(proyekService.findOne(proyekLokasiData.getProyekId()));
        proyekLokasi.setLokasi(lokasiService.findOne(proyekLokasiData.getLokasiId()));

        responseData.setStatus(true);
        responseData.setPayload(proyekLokasiService.save(proyekLokasi));
        return ResponseEntity.ok(responseData);
    }

    // Endpoint untuk menghapus relasi berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        ProyekLokasi proyekLokasi = proyekLokasiService.findOne(id);
        if (proyekLokasi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        proyekLokasiService.removeOne(id);
        return ResponseEntity.noContent().build();
    }
}
