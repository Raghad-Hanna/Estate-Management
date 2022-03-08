package com.raghad.estate.models;


import org.springframework.stereotype.Component;

import java.util.Date;

public interface LoggedResource {
    long getID();
    Date getCreatedAt();
    String getCreatedBy();

}
