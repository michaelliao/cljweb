create table courses (
    id varchar(32) not null primary key,
    name varchar(50) not null,
    price real not null,
    online bool not null,
    days bigint not null
);
