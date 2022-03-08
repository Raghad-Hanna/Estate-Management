package com.raghad.estate.controllers;

import com.google.gson.Gson;
import com.raghad.estate.models.ResourceCRUDOperationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/resource-crud-operations-details")
public class ResourceCRUDOperationDetailsController {
    private Gson gson;

    @Autowired
    public ResourceCRUDOperationDetailsController(Gson gson) {
        this.gson = gson;
    }

    @GetMapping
    public List<ResourceCRUDOperationDetails> readResourceCRUDOperationsDetails()
            throws FileNotFoundException, IOException {
        File logFile =
                new File("C:\\Users\\Raghad\\Desktop\\estate\\estate\\src\\main\\java\\com\\raghad\\estate\\resource-crud-operations-log.txt");

        BufferedReader reader
                = new BufferedReader(new FileReader(logFile));

        String detailsAsJson;
        ResourceCRUDOperationDetails detailsAsObject;
        List<ResourceCRUDOperationDetails> resourceCRUDOperationsDetails = new ArrayList<>();

        while((detailsAsJson = reader.readLine()) != null) {
            detailsAsObject = this.gson.fromJson(detailsAsJson, ResourceCRUDOperationDetails.class);
            resourceCRUDOperationsDetails.add(detailsAsObject);
        }

        reader.close();
        return resourceCRUDOperationsDetails;
    }
}
