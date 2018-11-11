# User schema

# --- !Ups
create table `user_auth` (
  `user_id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `is_activated` TINYINT(1) UNSIGNED NOT NULL DEFAULT,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

create table `user_auth_password` (
  `user_id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `hasher` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `password_salt` VARCHAR(255) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

alter table `user` add unique (`email`);

# --- !Downs
drop table `user_auth`;
drop table `user_auth_password`;
alter table `user` drop index `email`;
