package com.boon.boonapp.dao;

import com.boon.boonapp.model.Location;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long>, JpaSpecificationExecutor<Location> {

    List<Location> findAll();
}
