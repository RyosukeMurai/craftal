# User schema

# --- !Ups
create table `user_auth_token` (
  `token` VARCHAR(36) NOT NULL PRIMARY KEY,
  `user_id` INT UNSIGNED NOT NULL,
  `expired_at` DATETIME NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

# --- !Downs
drop table `user_auth_token`;
