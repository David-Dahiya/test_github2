package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.RepositoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryInfoRepository extends JpaRepository<RepositoryInfo, Long> {
}
