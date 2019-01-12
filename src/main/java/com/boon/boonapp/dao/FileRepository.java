package com.boon.boonapp.dao;

import com.boon.boonapp.model.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileMetadata, Long>, JpaSpecificationExecutor<FileMetadata> {
}
