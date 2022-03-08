package com.raghad.estate.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResourceCRUDOperationDetails {
    private String resourceType;
    private long resourceID;
    private CRUDOperationType operationType;
    private Date operationDate;
    private String operator;
}
