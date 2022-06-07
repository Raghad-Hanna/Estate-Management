package com.raghad.estate.models;

import java.util.Date;

public interface LoggedResource {
    long getID();
    Date getCreatedAt();
    String getCreatedBy();

}
