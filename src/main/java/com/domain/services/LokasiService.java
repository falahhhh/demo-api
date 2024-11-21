package com.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Lokasi;
import com.domain.models.repos.LokasiRepo;

@Service
public class LokasiService {

    @Autowired
    private LokasiRepo lokasiRepo;


    @Cacheable(value = "lokasiCache", key = "'allLocations'")
    public Iterable<Lokasi> findAll() {
        return lokasiRepo.findAll();
    }


    @Cacheable(value = "lokasiCache", key = "#id")
    public Lokasi findOne(Long id) {
        return lokasiRepo.findById(id).orElse(null);
    }


    @CacheEvict(value = "lokasiCache", key = "'allLocations'")
    public Lokasi save(Lokasi lokasi) {
        return lokasiRepo.save(lokasi);
    }


    public void removeOne(Long id){
        lokasiRepo.deleteById(id);
    }
}
