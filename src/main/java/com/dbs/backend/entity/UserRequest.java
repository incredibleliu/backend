package com.dbs.backend.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_REQUEST")
public class UserRequest {
	
//  request_id varchar(20) not null,
//  submitted_by varchar(10) not null,
//  submitted_date timestamp not null,
//  approved_by varchar(10),
//  approval_date timestamp,
//  status varchar(10) not null,
//  account_number varchar(20) not null  
	
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
	@Id
	private String requestId;
	private String submittedBy;
	private Timestamp submittedDate;
	private String approvedBy;
	private Timestamp approvalDate;
	private String status;
	private String accountNumber;
	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getSubmittedBy() {
		return submittedBy;
	}
	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}
	public Timestamp getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(Timestamp submittedDate) {
		this.submittedDate = submittedDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Timestamp getApprovalDate() {
		return approvalDate;
	}
	public void setApprovedDate(Timestamp approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "UserRequest [requestId=" + requestId + ", submittedBy=" + submittedBy
				+ ", submittedDate=" + submittedDate + ", approvedBy=" + approvedBy + ", approvalDate=" + approvalDate
				+ ", status=" + status + ", accountNumber=" + accountNumber + "]";
	}
	




}
