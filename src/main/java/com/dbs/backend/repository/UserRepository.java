package com.dbs.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.backend.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

}
