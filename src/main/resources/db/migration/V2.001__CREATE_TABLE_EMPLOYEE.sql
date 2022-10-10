create table employee (
    id integer primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(256) not null,
    address varchar(100),
    salary bigint,
    companyId bigint,
    FOREIGN KEY (companyId) REFERENCES company(id)
    );
    
 