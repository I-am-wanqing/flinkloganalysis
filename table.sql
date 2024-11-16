create table logs
(
    id        bigint auto_increment
        primary key,
    ip        varchar(45)  not null,
    identity  varchar(255) null,
    username  varchar(255) null,
    timestamp datetime     not null,
    request   text         null,
    status    int          not null,
    size      int          not null
);

create table visit_count
(
    id           int auto_increment
        primary key,
    window_start timestamp not null,
    window_end   timestamp not null,
    visit_count  bigint    not null
);

create table response_count
(
    id             int auto_increment
        primary key,
    window_start   timestamp not null,
    window_end     timestamp not null,
    status_code    int       not null,
    response_count bigint    not null
);

