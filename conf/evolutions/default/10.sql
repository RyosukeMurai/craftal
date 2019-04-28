# User schema

# --- !Ups
create table `attribute` (
  `id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL
);

create table `artist_attribute` (
  `id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `artist_id` INT UNSIGNED NOT NULL,
  `attribute_id` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (artist_id) REFERENCES user(id),
  FOREIGN KEY (attribute_id) REFERENCES attribute(id)
);

insert into `attribute` (id, name) values(1, '一点もの');
insert into `attribute` (id, name) values(2, '男性向け');
insert into `attribute` (id, name) values(3, '女性向け');
insert into `attribute` (id, name) values(4, '子供向け');
insert into `attribute` (id, name) values(5, 'ナチュラル系');
insert into `attribute` (id, name) values(6, 'カントリー系');
insert into `attribute` (id, name) values(7, 'ワークショップ');
insert into `attribute` (id, name) values(8, '食品（現場調理あり）');
insert into `attribute` (id, name) values(9, '食品（現場調理なし）');
insert into `attribute` (id, name) values(10, 'ファミリー向け');
insert into `attribute` (id, name) values(11, 'オーダーメイド可');
insert into `attribute` (id, name) values(12, '実演あり');

alter table `artist` add column `self_introduction` text not null;

# --- !Downs
drop table `attribute`;
drop table `artist_attribute`;
alter table `artist` drop column `self_introduction`;
