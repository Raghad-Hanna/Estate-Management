package com.raghad.estate.models;

import javax.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EstateUpdate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long ID;
    @ManyToOne
    private Estate updatedEstate;
    private Date updatedAt;
    private String updatedBy;
}
