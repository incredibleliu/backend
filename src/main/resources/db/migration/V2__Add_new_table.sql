create table exclusion_account (
    account_number varchar(20) not null primary key
);

CREATE UNIQUE INDEX idx_account_number ON exclusion_account
(
    account_number
 );

