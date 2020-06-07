package com.dbs.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.backend.entity.ExclusionAccount;
import com.dbs.backend.repository.ExclusionAccountRepository;

@Service
public class ExclusionAccountService {
	
	private static Logger log = LoggerFactory.getLogger(ExclusionAccountService.class);
	
	@Autowired
	private ExclusionAccountRepository exclusionAccountRepository;

	@Transactional
	public boolean save(ExclusionAccount ea) {
		
		log.info("ExclusionAccount: {}", ea.toString());
		exclusionAccountRepository.saveAndFlush(ea);
		log.info("exclusionAccountRepository.count(): {}", exclusionAccountRepository.count());
		return true;
	
	}
	
}
