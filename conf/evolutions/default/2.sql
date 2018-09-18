# User schema

# --- !Ups
insert into `event_status` (id, code) values(1, 'draft');
insert into `event_status` (id, code) values(2, 'applicable');
insert into `event_status` (id, code) values(3, 'confirmed');

insert into `event_location` (id, code) values(1, 'outdoor');
insert into `event_location` (id, code) values(2, 'indoor');

# --- !Downs
delete from `event_status` where `id` = 1;
delete from `event_status` where `id` = 2;
delete from `event_status` where `id` = 3;

delete from `event_location` where `id` = 1;
delete from `event_location` where `id` = 2;
