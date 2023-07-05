package com.thunder.light.database.repository;

import com.thunder.light.database.entity.StateEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends PagingAndSortingRepository<StateEntity, Long> {

    Optional<StateEntity> findById(Long id);

    List<StateEntity> findAllByNameContains(String titleValue);
}
