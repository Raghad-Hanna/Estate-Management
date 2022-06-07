package com.raghad.estate.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.raghad.estate.models.Estate;
import com.raghad.estate.services.EstateService;

@RestController
public class EstateController {
    private EstateService estateService;

    @Autowired
    public EstateController(EstateService estateService) {
        this.estateService = estateService;
    }

    @PostMapping("api/estates")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Estate createEstate(@RequestBody Estate estate) {
        return this.estateService.saveForSaleEstate(estate);
    }

    @GetMapping("api/sold-estates")
    public List<Estate> readSoldEstates() {
        return this.estateService.getByStatus("Sold");
    }

    @GetMapping("api/for-sale-estates")
    public List<Estate> readForSaleEstates() {
        return this.estateService.getByStatus("For Sale");
    }

    @PutMapping("api/estates/{ID}")
    public Estate updateEstate(@RequestBody Estate estate, @PathVariable long ID) {
        return this.estateService.update(estate, ID);
    }

    @DeleteMapping("api/estates/{ID}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEstate(@PathVariable long ID) {
        this.estateService.deleteById(ID);
    }
}
