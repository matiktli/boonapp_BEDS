package com.boon.boonapp.dao;

import com.boon.boonapp.model.Token;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TokenRepository extends PagingAndSortingRepository<Token, Long>, JpaSpecificationExecutor<Token> {

    Set<Token> findAllByUserId(Long userId);

    Optional<Token> getTokenByValue(String value);
}
