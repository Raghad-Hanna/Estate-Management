package com.raghad.estate.aspects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;
import com.raghad.estate.models.*;
import com.raghad.estate.repositories.EstateUpdateRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ResourceCRUDOperationDetailsLogger {
    private EstateUpdateRepository estateUpdateRepository;
    private Gson gson;

    private File logFile =
            new File("C:\\Users\\Raghad\\Desktop\\estate\\estate\\src\\main\\java\\com\\raghad\\estate\\resource-crud-operations-log.txt");

    @Autowired
    public ResourceCRUDOperationDetailsLogger(EstateUpdateRepository estateUpdateRepository,
                                              Gson gson) {
        this.estateUpdateRepository = estateUpdateRepository;
        this.gson = gson;
    }

    @Around("@annotation(com.raghad.estate.annotations.WithResourceCreationLogger)")
    public Object logResourceCreationInfo(final ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        LoggedResource resource = (LoggedResource)proceedingJoinPoint.proceed();

        ResourceCRUDOperationDetails details =
                this.getDetails(resource.getClass().getSimpleName(), resource.getID(),
                        CRUDOperationType.CREATE, resource.getCreatedAt(), resource.getCreatedBy());

        this.convertAndSave(details);
        return resource;
    }

    @Around("@annotation(com.raghad.estate.annotations.WithResourceUpdateLogger)")
    public Object logResourceUpdateInfo(final ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        LoggedResource resource = (LoggedResource)proceedingJoinPoint.proceed();

        EstateUpdate lastUpdate = estateUpdateRepository
                .findByUpdatedEstate((Estate)resource).get(0);

        ResourceCRUDOperationDetails details =
                this.getDetails(resource.getClass().getSimpleName(), resource.getID(),
                        CRUDOperationType.UPDATE, lastUpdate.getUpdatedAt(), lastUpdate.getUpdatedBy());

        this.convertAndSave(details);
        return resource;
    }

    @Around("@annotation(com.raghad.estate.annotations.WithResourceDeletionLogger)")
    public Object logResourceDeletionInfo(final ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        long resourceID = (long)proceedingJoinPoint.proceed();

        ResourceCRUDOperationDetails details =
                this.getDetails("Estate", resourceID,
                        CRUDOperationType.DELETE, new Date(),
                        SecurityContextHolder.getContext().getAuthentication().getName());

        this.convertAndSave(details);
        return resourceID;
    }

    private ResourceCRUDOperationDetails getDetails(String resourceType, long resourceID,
                                                    CRUDOperationType operationType,
                                                    Date operationDate,
                                                    String operator) {
        ResourceCRUDOperationDetails details =
                new ResourceCRUDOperationDetails();

        details.setResourceType(resourceType);
        details.setResourceID(resourceID);
        details.setOperationType(operationType);
        details.setOperationDate(operationDate);
        details.setOperator(operator);

        return details;
    }

    private void convertAndSave(ResourceCRUDOperationDetails details) throws IOException {
        String detailsAsJSON = this.gson.toJson(details);
        this.logToFile(detailsAsJSON);
    }

    private void logToFile(String logInfo) throws IOException {
        FileWriter writer = new FileWriter(this.logFile, true);
        writer.write(logInfo);
        writer.write("\n");
        writer.close();
    }
}
