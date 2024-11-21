package com.domain.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.LokasiData;
import com.domain.dto.ResponseData;
import com.domain.models.entities.Lokasi;
import com.domain.services.LokasiService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lokasi")
public class LokasiController {

    @Autowired
    private LokasiService lokasiService;

    @Autowired
    public ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Lokasi>> create(@Valid @RequestBody LokasiData lokasiData, Errors errors) {
        ResponseData<Lokasi> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Lokasi lokasi = modelMapper.map(lokasiData, Lokasi.class);

        responseData.setStatus(true);
        responseData.setPayload(lokasiService.save(lokasi));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Lokasi> findAll() {
        return lokasiService.findAll();
    }

    @GetMapping("/{id}")
    public Lokasi findOne(@PathVariable("id") Long id) {
        return lokasiService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Lokasi>> update(@Valid @RequestBody LokasiData lokasiData, Errors errors) {
        ResponseData<Lokasi> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Lokasi lokasi = modelMapper.map(lokasiData, Lokasi.class);

        responseData.setStatus(true);
        responseData.setPayload(lokasiService.save(lokasi));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        lokasiService.removeOne(id);
    }
}
