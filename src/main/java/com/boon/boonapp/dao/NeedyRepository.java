package com.boon.boonapp.dao;

import com.boon.boonapp.model.Needy;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeedyRepository extends PagingAndSortingRepository<Needy, Long>, JpaSpecificationExecutor<Needy> {
}
