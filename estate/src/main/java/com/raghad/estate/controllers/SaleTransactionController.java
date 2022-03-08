package com.raghad.estate.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.raghad.estate.models.SaleTransaction;
import com.raghad.estate.services.SaleTransactionService;

@RestController
public class SaleTransactionController {
    private SaleTransactionService saleTransactionService;

    @Autowired
    public SaleTransactionController(SaleTransactionService saleTransactionService) {
        this.saleTransactionService = saleTransactionService;
    }

    @PostMapping("api/sale-transactions/{estate-to-be-sold-ID}")
    public SaleTransaction createSaleTransaction(@RequestBody SaleTransaction saleTransaction,
                                        @PathVariable(name = "estate-to-be-sold-ID") long ID) {
        return this.saleTransactionService.sellEstate(saleTransaction, ID);
    }
}
