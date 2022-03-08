package com.raghad.estate.models;

import javax.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SaleTransaction implements LoggedResource {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long ID;
    @OneToOne
    private Estate soldEstate;
    private String buyerName;
    private double salePrice = -1;
    private Date saleDate;
    private Date createdAt;
    private String createdBy;
}
