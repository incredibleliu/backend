package com.dbs.backend.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.backend.entity.ExclusionAccount;
import com.dbs.backend.entity.UserRequest;
import com.dbs.backend.repository.ExclusionAccountRepository;
import com.dbs.backend.service.UserRequestService;
import com.dbs.backend.util.DateUtil;

import constants.Status;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class AccountController {
	
	private static Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private ExclusionAccountRepository accountRepository;
	
	@Autowired
	UserRequestService userRequestService;
	
	@Autowired
	DateUtil dateUtil;
	
	@GetMapping("/accounts")
	public List<ExclusionAccount> getAccounts(){
		
		List<ExclusionAccount> accounts = new ArrayList<ExclusionAccount>();
		log.info("enter AccountController.getAccounts...");
		InputStream in;
		try {
			in = new ClassPathResource(
			      "db/migration/account_number.csv").getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			for (String line; (line = reader.readLine()) != null; ) {
				if(!line.startsWith("account")) {
					log.info(line);
					ExclusionAccount ea = new ExclusionAccount(line);
					accounts.add(ea);					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("getAccounts return: {}", accounts);
		return accounts;
	}
	
	@PostMapping("/accounts")
	@Transactional
	public boolean createUserRequest(@Valid @RequestBody String accountsJson) {
		try {
			log.info(accountsJson);
			JSONArray jsonData = new JSONArray(accountsJson.trim());
			//get user
			JSONObject jsonObj = jsonData.getJSONObject(jsonData.length() - 1);
			String user = jsonObj.getString("accountNumber");
			log.info("user: " + user);
			//last item ignore
			for(int i=0; i<jsonData.length() - 1; i++) {
			    JSONObject obj = jsonData.getJSONObject(i);
				String accountNumber = obj.get("accountNumber") + "";
				UserRequest ur = new UserRequest();
				log.info("{}", accountNumber);
				ur.setAccountNumber(accountNumber);
				ur.setSubmittedBy(user);
				ur.setSubmittedDate(new Timestamp(new Date().getTime()));
				ur.setStatus(Status.pending + "");
				UUID uuid = UUID.randomUUID();
		        String randomUUIDString = uuid.toString();
				ur.setRequestId(randomUUIDString);
				userRequestService.save(ur);
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@GetMapping("/account/{id}")
	public ExclusionAccount getAccount(@PathVariable Long id){
		//return accountRepository.findById(id);
		return accountRepository.findById(id).isPresent()? accountRepository.findById(id).get() : null;
	}
	

	@DeleteMapping("/account/{id}")
	public boolean deleteAccount(@PathVariable Long id) {
		accountRepository.deleteById(id);
		return true;
	}
	
//	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
	
//	@PostMapping("/account")
	@PostMapping(path = "/account", consumes = "application/json", produces = "application/json")
	public ExclusionAccount createAccount(@RequestBody ExclusionAccount account){
		return accountRepository.save(account);
	}
	
//	@PutMapping("/account")
	@PutMapping(path = "/account", consumes = "application/json", produces = "application/json")
	public ExclusionAccount updateAccount(@RequestBody ExclusionAccount account){
		return accountRepository.save(account);
	}
	
	

}
