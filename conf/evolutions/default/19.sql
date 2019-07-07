# User schema

# --- !Ups
create table `event_follower` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `event_id` INT UNSIGNED NOT NULL,
  `follower_id` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  UNIQUE (event_id, follower_id),
  FOREIGN KEY (event_id) REFERENCES event(id),
  FOREIGN KEY (follower_id) REFERENCES user(id)
);

# --- !Downs
drop table `event_follower`;
