package com.raghad.estate.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.raghad.estate.models.DefaultValue;
import com.raghad.estate.services.DefaultValueService;

@RestController
@RequestMapping("api/default-values")
public class DefaultValueController {
    private DefaultValueService defaultValueService;

    @Autowired
    public DefaultValueController(DefaultValueService defaultValueService) {
        this.defaultValueService = defaultValueService;
    }

    @GetMapping
    public DefaultValue readDefaultValue() {
        return this.defaultValueService.getDefaultValue();
    }

    @PutMapping
    public DefaultValue updateDefaultValue(@RequestBody DefaultValue defaultValue) {
        return this.defaultValueService.update(defaultValue);
    }
}
