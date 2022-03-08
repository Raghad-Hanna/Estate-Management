package com.raghad.estate.models;

import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
@NoArgsConstructor
public class DefaultValue {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long ID;
    private int shareCount;
    private double profitRate;
}
