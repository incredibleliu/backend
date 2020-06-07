create table user (
	id IDENTITY PRIMARY KEY,
    name varchar(20) not null,
    password varchar(10) not null,
    role varchar(10) not null
);

INSERT INTO user (name, password, role) VALUES ('tom', 'mot', 'user');
INSERT INTO user (name, password, role) VALUES ('john', 'nhoj', 'admin');
