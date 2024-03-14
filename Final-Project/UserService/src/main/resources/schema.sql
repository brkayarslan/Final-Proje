create table REVIEW_TABLE (
                              id bigint generated by default as identity,
                              createDate timestamp(6),
                              updateDate timestamp(6),
                              comment varchar(255),
                              deliveryScore integer check ((deliveryScore<=5) and (deliveryScore>=1)),
                              foodScore integer check ((foodScore<=5) and (foodScore>=1)),
                              presentationScore integer check ((presentationScore<=5) and (presentationScore>=1)),
                              restaurantId varchar(255),
                              reviewDate date,
                              user_id bigint,
                              primary key (id)
);

create table USER_TABLE
(
    id         bigint generated by default as identity,
    createDate timestamp(6),
    updateDate timestamp(6),
    E_MAIL     varchar(255),
    FIRST_NAME varchar(100) not null,
    LAST_NAME  varchar(100) not null,
    LATITUDE   float(53)    not null,
    LONGITUDE  float(53)    not null,
    PASSWORD   varchar(400) not null,
    TELEPHONE  varchar(255),
    primary key (id)
);