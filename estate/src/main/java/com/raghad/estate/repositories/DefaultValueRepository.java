package com.raghad.estate.repositories;

import com.raghad.estate.models.DefaultValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultValueRepository extends JpaRepository<DefaultValue, Long> {
}
