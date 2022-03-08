package com.raghad.estate.repositories;

import com.raghad.estate.models.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Long> {
    List<Estate> findByStatus(String status);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select e from Estate e where e.ID=:id")
    Optional<Estate> findByIdWithPessimisticLock(long id);
}
