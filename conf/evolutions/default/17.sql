# User schema

# --- !Ups
create table `artist_follower` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `artist_id` INT UNSIGNED NOT NULL,
  `follower_id` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  UNIQUE (artist_id, follower_id),
  FOREIGN KEY (artist_id) REFERENCES artist(user_id),
  FOREIGN KEY (follower_id) REFERENCES user(id)
);

alter table `event` add column `home_page_url` varchar(255) null;
alter table `event` add column `twitter_url` varchar(255) null;
alter table `event` add column `instagram_url` varchar(255) null;
alter table `event` add column `facebook_url` varchar(255) null;

alter table `artist_attribute` drop foreign key `artist_attribute_ibfk_1`;
alter table `artist_attribute` add constraint `artist_attribute_ibfk_1` foreign key (artist_id) references artist(user_id);

alter table `artist_photo` drop foreign key `artist_photo_ibfk_1`;
alter table `artist_photo` add constraint `artist_photo_ibfk_1` foreign key (artist_id) references artist(user_id);

alter table `event_artist` drop foreign key `event_artist_ibfk_2`;
alter table `event_artist` add constraint `event_artist_ibfk_2` foreign key (artist_id) references artist(user_id);

alter table `event_schedule` modify prefecture_id int unsigned not null default 13 after event_id; -- Tokyo
alter table `event_schedule` modify num_of_booths int unsigned not null default 0 after prefecture_id;
alter table `event_schedule` add column `address` varchar(255) not null;
alter table `event_schedule` add column `postal_code` varchar(8) not null;
alter table `event_schedule` add column `how_to_access` text not null;
alter table `event_schedule` add column `venue_url` varchar(255) null;
alter table `event_schedule` add column `venue_remarks` text not null;

create table `attribute_type` (
  `id` TINYINT UNSIGNED NOT NULL PRIMARY KEY,
  `code` ENUM('artist', 'event_for_guest', 'event_for_participant') NOT NULL
);

insert into `attribute_type` (id, code) values(1, 'artist');
insert into `attribute_type` (id, code) values(2, 'event_for_guest');
insert into `attribute_type` (id, code) values(3, 'event_for_participant');

alter table `attribute` add column `type_id` tinyint unsigned not null;
update `attribute` set `type_id` = 1 where `id` between 1 and 12;
alter table `attribute` add constraint `attribute_ibfk_1` foreign key (type_id) references attribute_type(id);

insert into `attribute` (id, name, type_id) values(13, '駅チカ', 2);
insert into `attribute` (id, name, type_id) values(14, '小雨決行', 2);
insert into `attribute` (id, name, type_id) values(15, '雨天時開催', 2);
insert into `attribute` (id, name, type_id) values(16, '施設内駐車場あり', 2);
insert into `attribute` (id, name, type_id) values(17, '入場無料', 2);
insert into `attribute` (id, name, type_id) values(18, '入場料あり', 2);
insert into `attribute` (id, name, type_id) values(19, '食品（現場調理なし）出展可', 3);
insert into `attribute` (id, name, type_id) values(20, '飲食（現場調理あり）出展可', 3);
insert into `attribute` (id, name, type_id) values(21, 'ケータリングカー出展可', 3);
insert into `attribute` (id, name, type_id) values(22, 'テント貸出あり', 3);
insert into `attribute` (id, name, type_id) values(23, '机・椅子貸出あり', 3);
insert into `attribute` (id, name, type_id) values(24, '全ブース角地', 3);
insert into `attribute` (id, name, type_id) values(25, '2.5mテント可', 3);
insert into `attribute` (id, name, type_id) values(26, '3mテント可', 3);
insert into `attribute` (id, name, type_id) values(27, '電源使用可', 3);
insert into `attribute` (id, name, type_id) values(28, '先着順', 3);
insert into `attribute` (id, name, type_id) values(29, '審査制', 3);
insert into `attribute` (id, name, type_id) values(30, 'グループ出展可', 3);


create table `event_attribute` (
  `id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `event_id` INT UNSIGNED NOT NULL,
  `attribute_id` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (event_id) REFERENCES event(id),
  FOREIGN KEY (attribute_id) REFERENCES attribute(id)
);

alter table `event_artist` add column `comment` text not null;

alter table `photo` add column `caption` varchar(255) not null;

create table `organizer` (
  `user_id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `phone_number` varchar(255) null,
  `fax_number` varchar(255) null,
  `address` varchar(255) not null,
  `postal_code` varchar(8) not null,
  `home_page_url` varchar(255) null,
  `twitter_url` varchar(255) null,
  `instagram_url` varchar(255) null,
  `facebook_url` varchar(255) null,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

# --- !Downs
drop table `artist_follower`;

alter table `event` drop column `home_page_url`;
alter table `event` drop column `twitter_url`;
alter table `event` drop column `instagram_url`;
alter table `event` drop column `facebook_url`;

alter table `artist_attribute` drop foreign key `artist_attribute_ibfk_1`;
alter table `artist_attribute` add constraint `artist_attribute_ibfk_1` foreign key (artist_id) references user(id);

alter table `artist_photo` drop foreign key `artist_photo_ibfk_1`;
alter table `artist_photo` add constraint `artist_photo_ibfk_1` foreign key (artist_id) references user(id);

alter table `event_artist` drop foreign key `event_artist_ibfk_2`;
alter table `event_artist` add constraint `event_artist_ibfk_2` foreign key (artist_id) references user(id);

alter table `event_schedule` modify prefecture_id int unsigned not null default 13 after is_deleted; -- Tokyo
alter table `event_schedule` modify num_of_booths int unsigned not null default 0 after prefecture_id;
alter table `event_schedule` drop column `address`;
alter table `event_schedule` drop column `postal_code`;
alter table `event_schedule` drop column `how_to_access`;
alter table `event_schedule` drop column `venue_url`;
alter table `event_schedule` drop column `venue_remarks`;

alter table `attribute` drop foreign key `attribute_ibfk_1`;
alter table `attribute` drop column `type_id`;
delete from `attribute` where `id` >= 13;
drop table `attribute_type`;

drop table `event_attribute`;

alter table `event_artist` drop column `comment`;

alter table `photo` drop column `caption`;

drop table `organizer`;
