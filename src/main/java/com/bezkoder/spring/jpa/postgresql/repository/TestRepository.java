package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.TeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TeamInfo, Long> {
}
