create sequence hibernate_sequence start 1 increment 1;
create table IF NOT EXISTS mobile_application (
   id int8 not null,
   version varchar(255),
   primary key (id)
);
create table IF NOT EXISTS phone (
    id int8 not null,
    is_active boolean,
    number varchar(255),
    mobile_application_id int8,
    primary key (id)
);
create table IF NOT EXISTS push_notification (
    id int8 not null,
    is_active boolean,
    body varchar(255),
    date timestamp,
    title varchar(255),
    primary key (id)
);
create table IF NOT EXISTS push_notification_phones (
    push_notification_id int8 not null,
    phones_id int8 not null
);
alter table if exists phone add constraint phone_fk foreign key (mobile_application_id) references mobile_application;
alter table if exists push_notification_phones add constraint push_notification_phones_fk_phone foreign key (phones_id) references phone;
alter table if exists push_notification_phones add constraint push_notification_phones_fk_notify foreign key (push_notification_id) references push_notification;