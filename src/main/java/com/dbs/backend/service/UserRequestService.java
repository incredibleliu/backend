package com.dbs.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.backend.entity.UserRequest;
import com.dbs.backend.repository.UserRequestRepository;

@Service
public class UserRequestService {
	
	private static Logger log = LoggerFactory.getLogger(UserRequestService.class);
	
	@Autowired
	private UserRequestRepository userRequestRepository;

	@Transactional
	public boolean save(UserRequest ur) {
		
		log.info(ur.toString());
		userRequestRepository.saveAndFlush(ur);
		log.info("userRequestRepository.count(): {}", userRequestRepository.count());
		return true;
	
	}

	public List<UserRequest> getUserRequests(String condition) {
		
		if("all".equals(condition)) {
			log.info("query all");
			return userRequestRepository.findAll();
		
		} else if("pending".equals(condition)) {
			log.info("query pending");
			return userRequestRepository.findByStatus("pending");
		
		} else {
			return null;
		}
		
	}

}
