# User schema

# --- !Ups
create table `account` (
  `user_id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `password` VARCHAR(255) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES user(id)
);
alter table `user` add unique (`email`);

# --- !Downs
drop table `account`;
alter table `user` drop index `email`;
