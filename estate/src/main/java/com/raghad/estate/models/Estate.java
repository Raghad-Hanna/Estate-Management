package com.raghad.estate.models;

import javax.persistence.*;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estate implements LoggedResource {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long ID;
    private String location;
    private double price;
    private int shareCount = -1;
    private String status;
    private Date createdAt;
    private String createdBy;
}
