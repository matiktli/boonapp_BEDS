package com.boon.boonapp.dao;

import com.boon.boonapp.model.Help;
import com.boon.boonapp.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpRepository extends PagingAndSortingRepository<Help, Long>, JpaSpecificationExecutor<Help> {
}
