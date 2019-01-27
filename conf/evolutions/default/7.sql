# User schema

# --- !Ups
create table `role` (
  `id` TINYINT UNSIGNED NOT NULL PRIMARY KEY,
  `name` ENUM('管理者', 'イベンター', '作家', '会員') NOT NULL,
  `code` ENUM('administrator', 'eventer', 'artist', 'member') NOT NULL,
  UNIQUE (code)
);

create table `user_role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` INT UNSIGNED NOT NULL,
  `role_id` TINYINT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  UNIQUE (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (role_id) REFERENCES role(id)
);

create table `permission` (
  `id` TINYINT UNSIGNED NOT NULL PRIMARY KEY,
  `name` ENUM('管理者', 'イベンター', '作家', '会員') NOT NULL,
  `code` ENUM('administrator', 'eventer', 'artist', 'member') NOT NULL,
  UNIQUE (code)
);


create table `role_permission` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `role_id` TINYINT UNSIGNED NOT NULL,
  `permission_id` TINYINT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  UNIQUE (role_id, permission_id),
  FOREIGN KEY (role_id) REFERENCES role(id),
  FOREIGN KEY (permission_id) REFERENCES permission(id)
);

insert into `role` (id, name, code) values(1, '管理者', 'administrator');
insert into `role` (id, name, code) values(2, 'イベンター', 'eventer');
insert into `role` (id, name, code) values(3, '作家', 'artist');
insert into `role` (id, name, code) values(4, '会員', 'member');

# --- !Downs
drop table `user_role`;
drop table `role`;
drop table `user_permission`;
drop table `permission`;

delete from `role` where `id` = 1;
delete from `role` where `id` = 2;
delete from `role` where `id` = 3;
delete from `role` where `id` = 4;
