# User schema

# --- !Ups
alter table `event_schedule` change `state_time` `start_time` datetime not null;
alter table `event_schedule` add column `prefecture_id` int unsigned not null default 13; -- Tokyo
alter table `event_schedule` add constraint `event_schedule_ibfk_2` foreign key (prefecture_id) references prefecture(id);

# --- !Downs
alter table `event_schedule` change `start_time` `state_time` datetime not null;
alter table `event_schedule` drop foreign key `event_schedule_ibfk_2`;
alter table `event_schedule` drop column `prefecture_id`;
