package com.dbs.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.backend.entity.ExclusionAccount;

@Repository
@Transactional
public interface ExclusionAccountRepository extends JpaRepository<ExclusionAccount, Long> {

}
