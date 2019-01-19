package com.boon.boonapp.dao;

import com.boon.boonapp.model.Needy;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeedyRepository extends PagingAndSortingRepository<Needy, Long>, JpaSpecificationExecutor<Needy> {

    List<Needy> findAll();
}
