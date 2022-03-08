package com.raghad.estate.services;

import java.util.Date;
import java.util.List;

import com.raghad.estate.annotations.WithResourceCreationLogger;
import com.raghad.estate.annotations.WithResourceUpdateLogger;
import com.raghad.estate.annotations.WithResourceDeletionLogger;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.raghad.estate.models.Estate;
import com.raghad.estate.repositories.EstateRepository;
import com.raghad.estate.repositories.DefaultValueRepository;
import com.raghad.estate.models.EstateUpdate;
import com.raghad.estate.repositories.EstateUpdateRepository;

@Service
public class EstateService {
    private EstateRepository estateRepository;
    private DefaultValueRepository defaultValueRepository;
    private EstateUpdateRepository estateUpdateRepository;

    @Autowired
    public EstateService(EstateRepository estateRepository, DefaultValueRepository defaultValueRepository,
                         EstateUpdateRepository estateUpdateRepository) {
        this.estateRepository = estateRepository;
        this.defaultValueRepository = defaultValueRepository;
        this.estateUpdateRepository = estateUpdateRepository;
    }

    @WithResourceCreationLogger
    public Estate saveForSaleEstate(Estate estate) {
        if(estate.getShareCount() == -1)
            estate.setShareCount(this.defaultValueRepository.findById(1L).get().getShareCount());

        estate.setStatus("For Sale");
        estate.setCreatedAt(new Date());
        estate.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        this.estateRepository.save(estate);
        return estate;
    }

    public List<Estate> getByStatus(String status) {
        return this.estateRepository.findByStatus(status);
    }

    @WithResourceUpdateLogger
    public Estate update(Estate estate, long ID) {
        Estate persistedEstate = this.estateRepository.findById(ID).get();

        estate.setID(ID);

        if(estate.getLocation() == null)
            estate.setLocation(persistedEstate.getLocation());
        estate.setStatus(persistedEstate.getStatus());

        if(estate.getPrice() == 0.0D)
            estate.setPrice(persistedEstate.getPrice());

        if(estate.getShareCount() == -1)
            estate.setShareCount(persistedEstate.getShareCount());

        if(estate.getStatus() == null)
            estate.setStatus(persistedEstate.getStatus());

        estate.setCreatedAt(persistedEstate.getCreatedAt());
        estate.setCreatedBy(persistedEstate.getCreatedBy());
        this.estateRepository.save(estate);

        EstateUpdate estateUpdate = new EstateUpdate();
        estateUpdate.setUpdatedEstate(estate);
        estateUpdate.setUpdatedAt(new Date());
        estateUpdate.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        this.estateUpdateRepository.save(estateUpdate);

        return estate;
    }

    @WithResourceDeletionLogger
    public long deleteById(long ID) {
        this.estateRepository.deleteById(ID);
        return ID;
    }
}
