package com.raghad.estate.services;

import com.raghad.estate.annotations.WithResourceCreationLogger;
import com.raghad.estate.models.Estate;
import com.raghad.estate.models.SaleTransaction;
import com.raghad.estate.repositories.DefaultValueRepository;
import com.raghad.estate.repositories.EstateRepository;
import com.raghad.estate.repositories.SaleTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SaleTransactionService {
    private EstateRepository estateRepository;
    private SaleTransactionRepository saleTransactionRepository;
    private DefaultValueRepository defaultValueRepository;

    @Autowired
    public SaleTransactionService(SaleTransactionRepository saleTransactionRepository,
                                     EstateRepository estateRepository, DefaultValueRepository defaultValueRepository) {
        this.saleTransactionRepository = saleTransactionRepository;
        this.estateRepository = estateRepository;
        this.defaultValueRepository = defaultValueRepository;
    }

    @WithResourceCreationLogger
    @Transactional
    public SaleTransaction sellEstate(SaleTransaction saleTransaction, long ID) {
        Estate estateToBeSold = this.estateRepository.findByIdWithPessimisticLock(ID).get();

        if(estateToBeSold.getStatus().equals("Sold"))
            throw new RuntimeException("Estate Already Sold");

        estateToBeSold.setStatus("Sold");
        this.estateRepository.save(estateToBeSold);

        saleTransaction.setSoldEstate(estateToBeSold);

        if(saleTransaction.getSalePrice() == -1) {
            double estatePrice = estateToBeSold.getPrice();
            double profitRate = this.defaultValueRepository.findById(1L).get().getProfitRate();
            double salePrice = estatePrice * profitRate;
            saleTransaction.setSalePrice(salePrice);
        }

        saleTransaction.setSaleDate(new Date());
        saleTransaction.setCreatedAt(new Date());
        saleTransaction.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        this.saleTransactionRepository.save(saleTransaction);
        return saleTransaction;
    }
}
