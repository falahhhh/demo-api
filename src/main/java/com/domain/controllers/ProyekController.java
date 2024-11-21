package com.domain.controllers;

import java.util.List;

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

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.models.entities.Proyek;
import com.domain.services.ProyekService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proyek")
public class ProyekController {


    @Autowired
    private ProyekService proyekService;

    @PostMapping
    public ResponseEntity<ResponseData<Proyek>> create(@Valid @RequestBody Proyek proyek, Errors errors ){


        ResponseData<Proyek> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for ( ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

        }
        responseData.setStatus(true);
        responseData.setPayload(proyekService.save(proyek));
        return ResponseEntity.ok(responseData);
    }
    @GetMapping
    public Iterable<Proyek> findAll(){
        return proyekService.findAll();
    }

    @GetMapping("/{id}")
    public Proyek findOne(@PathVariable("id") Long id){
        return proyekService.findOne(id);
    }


    @PutMapping
    public ResponseEntity<ResponseData<Proyek>> update(@Valid @RequestBody Proyek proyek, Errors errors ){
        
        ResponseData<Proyek> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for ( ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

        }
        responseData.setStatus(true);
        responseData.setPayload(proyekService.save(proyek));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne (@PathVariable("id") Long id){
        proyekService.removeOne(id);
    }

    @PostMapping("/search/name")
    public Proyek getProyekByName(@RequestBody SearchData searchData) {
    return proyekService.getProyekByName(searchData.getSearchKey());
    }

    @PostMapping("/search/namelike")
    public List<Proyek> getProyekByNameLike(@RequestBody SearchData searchData) {
    return proyekService.getProyekByNameLike(searchData.getSearchKey());
    }
 
    
}
