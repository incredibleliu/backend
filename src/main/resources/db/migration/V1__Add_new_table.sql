create table user_request (
    request_id varchar(40) not null PRIMARY KEY,
    submitted_by varchar(10) not null,
    submitted_date timestamp not null,
    approved_by varchar(10),
    approval_date timestamp,
    status varchar(10) not null,
    account_number varchar(20) not null  
);

CREATE UNIQUE INDEX idx_user_request ON user_request
(
    submitted_by, status, account_number
 );

