# User schema

# --- !Ups
create table `prefecture` (
  `id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL
);

insert into `prefecture` (id, name) values(1, '北海道');
insert into `prefecture` (id, name) values(2, '青森');
insert into `prefecture` (id, name) values(3, '岩手');
insert into `prefecture` (id, name) values(4, '宮城');
insert into `prefecture` (id, name) values(5, '秋田');
insert into `prefecture` (id, name) values(6, '山形');
insert into `prefecture` (id, name) values(7, '福島');
insert into `prefecture` (id, name) values(8, '茨城');
insert into `prefecture` (id, name) values(9, '栃木');
insert into `prefecture` (id, name) values(10, '群馬');
insert into `prefecture` (id, name) values(11, '埼玉');
insert into `prefecture` (id, name) values(12, '千葉');
insert into `prefecture` (id, name) values(13, '東京');
insert into `prefecture` (id, name) values(14, '神奈川');
insert into `prefecture` (id, name) values(15, '新潟');
insert into `prefecture` (id, name) values(16, '富山');
insert into `prefecture` (id, name) values(17, '石川');
insert into `prefecture` (id, name) values(18, '福井');
insert into `prefecture` (id, name) values(19, '山梨');
insert into `prefecture` (id, name) values(20, '長野');
insert into `prefecture` (id, name) values(21, '岐阜');
insert into `prefecture` (id, name) values(22, '静岡');
insert into `prefecture` (id, name) values(23, '愛知');
insert into `prefecture` (id, name) values(24, '三重');
insert into `prefecture` (id, name) values(25, '滋賀');
insert into `prefecture` (id, name) values(26, '京都');
insert into `prefecture` (id, name) values(27, '大阪');
insert into `prefecture` (id, name) values(28, '兵庫');
insert into `prefecture` (id, name) values(29, '奈良');
insert into `prefecture` (id, name) values(30, '和歌山');
insert into `prefecture` (id, name) values(31, '鳥取');
insert into `prefecture` (id, name) values(32, '島根');
insert into `prefecture` (id, name) values(33, '岡山');
insert into `prefecture` (id, name) values(34, '広島');
insert into `prefecture` (id, name) values(35, '山口');
insert into `prefecture` (id, name) values(36, '徳島');
insert into `prefecture` (id, name) values(37, '香川');
insert into `prefecture` (id, name) values(38, '愛媛');
insert into `prefecture` (id, name) values(39, '高知');
insert into `prefecture` (id, name) values(40, '福岡');
insert into `prefecture` (id, name) values(41, '佐賀');
insert into `prefecture` (id, name) values(42, '長崎');
insert into `prefecture` (id, name) values(43, '熊本');
insert into `prefecture` (id, name) values(44, '大分');
insert into `prefecture` (id, name) values(45, '宮崎');
insert into `prefecture` (id, name) values(46, '鹿児島');
insert into `prefecture` (id, name) values(47, '沖縄');

create table `artist` (
  `user_id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `genre_id` INT UNSIGNED NOT NULL,
  `prefecture_id` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (genre_id) REFERENCES genre(id),
  FOREIGN KEY (prefecture_id) REFERENCES prefecture(id)
);

alter table `event_artist` drop foreign key `event_artist_ibfk_2`;
alter table `event_artist` change `user_id` `artist_id` int unsigned;
alter table `event_artist` add constraint `event_artist_ibfk_2` foreign key (artist_id) references user(id);

# --- !Downs
drop table `prefecture`;
drop table `artist`;
alter table `event_artist` drop foreign key `event_artist_ibfk_2`;
alter table `event_artist` change `artist_id` `user_id` int unsigned;
alter table `event_artist` add constraint `event_artist_ibfk_2` foreign key (artist_id) references user(id);
