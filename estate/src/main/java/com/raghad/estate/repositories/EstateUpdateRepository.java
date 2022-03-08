package com.raghad.estate.repositories;

import com.raghad.estate.models.Estate;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.raghad.estate.models.EstateUpdate;

import java.util.List;

@Repository
public interface EstateUpdateRepository extends JpaRepository<EstateUpdate, Long> {
    List<EstateUpdate> findByUpdatedEstate(Estate estate);
}
