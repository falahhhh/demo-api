package com.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Proyek;
import com.domain.models.repos.ProyekRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProyekService {

    @Autowired
    private ProyekRepo proyekRepo;

    public Proyek save(Proyek proyek){
        return proyekRepo.save(proyek);
    }

    public Proyek findOne(Long id){
        Optional<Proyek> proyek = proyekRepo.findById(id);
        if(!proyek.isPresent()){
            return null;
        }
        return proyek.get();
    }

    public Iterable<Proyek> findAll(){
        return proyekRepo.findAll();
    }

    public void removeOne(Long id){
        proyekRepo.deleteById(id);
    }


    public Proyek getProyekByName(String namaProyek) {
        return proyekRepo.findProyekByName(namaProyek);
    }

    public List<Proyek> getProyekByNameLike(String namaProyek) {
        return proyekRepo.findProyekByNameLike("%"+"%");
    }
    
}
