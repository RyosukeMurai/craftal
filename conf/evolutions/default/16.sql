# User schema

# --- !Ups
alter table `event_schedule` add column `num_of_booths` int unsigned not null default 0;

# --- !Downs
alter table `event_schedule` drop column `num_of_booths`;
