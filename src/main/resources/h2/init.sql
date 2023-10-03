create table users (
    id bigint auto_increment,
    name varchar(50),
    balance decimal(16, 4),
    primary key (id)
);

create table user_transaction (
    id bigint auto_increment,
    user_id bigint,
    amount decimal(16, 4),
    transaction_date timestamp,
    foreign key (user_id) references users(id) on delete cascade
);