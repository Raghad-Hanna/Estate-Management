package com.raghad.estate.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import com.raghad.estate.models.DefaultValue;
import com.raghad.estate.repositories.DefaultValueRepository;

@Service
public class DefaultValueService {
    private DefaultValueRepository defaultValueKeyRepository;

    @Autowired
    public DefaultValueService(DefaultValueRepository defaultValueRepository) {
        this.defaultValueKeyRepository = defaultValueRepository;
    }

    @Cacheable("DefaultValue")
    public DefaultValue getDefaultValue() {
        return this.defaultValueKeyRepository.findById(1L).get();
    }

    @CacheEvict(value = "DefaultValue", allEntries = true)
    public DefaultValue update(DefaultValue defaultValue) {
        defaultValue.setID(this.defaultValueKeyRepository.findById(1L).get().getID());
        this.defaultValueKeyRepository.save(defaultValue);
        return defaultValue;
    }
}
