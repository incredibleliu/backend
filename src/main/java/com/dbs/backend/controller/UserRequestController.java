package com.dbs.backend.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.backend.entity.ExclusionAccount;
import com.dbs.backend.entity.UserRequest;
import com.dbs.backend.service.ExclusionAccountService;
import com.dbs.backend.service.UserRequestService;
import com.dbs.backend.util.DateUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class UserRequestController {
	
	private static Logger log = LoggerFactory.getLogger(UserRequestController.class);
	
	@Autowired
	UserRequestService userRequestService;
	
	@Autowired
	ExclusionAccountService ExclusionAccountService;
	
	@Autowired
	DateUtil dateUtil;
	
	@GetMapping("/userRequests")
	public List<UserRequest> getUserRequests(){
		log.info("enter UserRequestController.getUserRequests...");
		return userRequestService.getUserRequests("all");
	}
	
	@GetMapping("/userRequests/pending")
	public List<UserRequest> getUserRequestsPending(){
		log.info("enter UserRequestController.getUserRequests...");
		return userRequestService.getUserRequests("pending");
	}
	
	@PostMapping("/userRequest")
	@Transactional
	public boolean updateUserRequest(@Valid @RequestBody String userRequestJson){
		log.info("enter UserRequestController.updateUserRequest...");
		log.info(userRequestJson);
		JSONObject jsonObj = new JSONObject(userRequestJson);
		UserRequest ur = new UserRequest();
		ur.setAccountNumber(jsonObj.getString("accountNumber"));
		if(jsonObj.get("approvedBy") != null) {
			ur.setApprovedBy(jsonObj.get("approvedBy") + "");
		}
		if(!jsonObj.get("approvalDate").equals(null)
				&& !"".equals(jsonObj.get("approvalDate"))
				&& !"null".equals(jsonObj.get("approvalDate"))) {
			log.info("approvalDate: {}", jsonObj.get("approvalDate"));
			ur.setApprovedDate(dateUtil.getTimestamp(jsonObj.get("approvalDate") + ""));
		}
		ur.setRequestId(jsonObj.getString("requestId"));
		if(!jsonObj.get("status").equals(null)
				&& !"".equals(jsonObj.get("status"))) {
			String status = jsonObj.get("status") + "";
			ur.setStatus(status);
			//approve
			if("approved".equals(status)) {
				log.info("approved proceed...");
				//save into db
				String accountNumber = jsonObj.getString("accountNumber");
				ExclusionAccount ea = new ExclusionAccount(accountNumber);
				ExclusionAccountService.save(ea);
				//remove from .csv
				InputStream in = null;
				BufferedReader br = null;
				FileWriter fw = null;
				BufferedWriter bw = null;
				
				try {

					in = new ClassPathResource(
			    		      "db/migration/account_number.csv").getInputStream();
			    	br = new BufferedReader(new InputStreamReader(in));
			    	StringBuilder sb = new StringBuilder();
					for (String line; (line = br.readLine()) != null; ) {
						log.info("file line: {}", line);
						sb.append(line + "\r\n");
					}
					log.info("sb : {}", sb.toString());
					String newContent = sb.toString().replaceAll(accountNumber + "\r\n", "");
					log.info("newContent: {}", newContent);
//					fw = new FileWriter(new ClassPathResource(
//			    		      "db/migration/account_number.csv").getFile(), false);
//			    	writer = new BufferedWriter(fw);
//			    	writer.write(newContent);
//			    	writer.flush();
//					URL url = this.getClass().getResource("src/main/resources/db/migration/account_number.csv");
//					log.info("url.toURI().getPath(): {}", url.toURI().getPath());
//					File f = new File(url.toURI().getPath());
					File f = new File("src/main/resources/db/migration/account_number.csv");
					log.info("f: {}", f);
					fw = new FileWriter(f.getAbsoluteFile(), false);
					bw = new BufferedWriter(fw);
					bw.write(newContent);
					bw.close();
					
//					fw.write(newContent);
//					fw.flush();
					
				} catch (Exception e) {
					e.printStackTrace();
					log.error("Error...", e);
					
				} finally {
					try {
						in.close();
						br.close();;
						fw.close();
						bw.close();

					} catch (Exception e) {
						e.printStackTrace();
						log.error("Error...", e);
					}
					
				}
				
			}
		}
		if(jsonObj.get("submittedBy") != null && !"".equals(jsonObj.get("submittedBy"))) {
			ur.setSubmittedBy(jsonObj.get("submittedBy") + "");
		}
		if(!jsonObj.get("submittedDate").equals(null)
				&& !"".equals(jsonObj.get("submittedDate"))
				&& !"null".equals(jsonObj.get("submittedDate"))) {
			ur.setSubmittedDate(dateUtil.getTimestamp(jsonObj.get("submittedDate") + ""));
		}

		return userRequestService.save(ur);
	}

}
