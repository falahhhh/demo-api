package com.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.domain.models.entities.ProyekLokasi;
import com.domain.models.repos.ProyekLokasiRepo;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProyekLokasiService {

    @Autowired
    private ProyekLokasiRepo proyekLokasiRepo;

    public ProyekLokasi save(ProyekLokasi proyekLokasi){
        return proyekLokasiRepo.save(proyekLokasi);
    }

    public ProyekLokasi findOne(Long id){
        Optional<ProyekLokasi> proyekLokasi = proyekLokasiRepo.findById(id);
        if(!proyekLokasi.isPresent()){
            return null;
        }
        return proyekLokasi.get();
    }

    public Iterable<ProyekLokasi> findAll(){
        return proyekLokasiRepo.findAll();
    }

    public void removeOne(Long id){
        proyekLokasiRepo.deleteById(id);
    }
    
}
