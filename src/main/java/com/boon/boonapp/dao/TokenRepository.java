package com.boon.boonapp.dao;

import com.boon.boonapp.model.Token;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends PagingAndSortingRepository<Token, Long>, JpaSpecificationExecutor<Token> {
}
