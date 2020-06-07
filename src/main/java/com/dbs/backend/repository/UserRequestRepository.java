package com.dbs.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.backend.entity.UserRequest;

@Repository
@Transactional
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {
	
	List<UserRequest> findByStatus(String status);

}
