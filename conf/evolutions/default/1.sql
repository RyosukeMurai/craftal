# User schema

# --- !Ups
create table `user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0
);

create table `event_status` (
  `id` TINYINT UNSIGNED NOT NULL PRIMARY KEY,
  `code` ENUM('draft', 'applicable', 'confirmed') NOT NULL
);

create table `event_location` (
  `id` TINYINT UNSIGNED NOT NULL PRIMARY KEY,
  `code` ENUM('outdoor', 'indoor') NOT NULL
);

create table `event` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `status_id` TINYINT UNSIGNED NOT NULL,
  `location_id` TINYINT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (status_id) REFERENCES event_status(id),
  FOREIGN KEY (location_id) REFERENCES event_location(id)
);

create table `photo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `file_identifier` VARCHAR(255) NOT NULL, -- TODO(RyosukeMurai): Set unique constraint after avoided key length error.
  `file_name` VARCHAR(255) NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES user(id)
);

create table `event_photo` (
  `id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `event_id` INT UNSIGNED NOT NULL,
  `photo_id` INT UNSIGNED NOT NULL,
  `position_no` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (event_id) REFERENCES event(id),
  FOREIGN KEY (photo_id) REFERENCES photo(id)
);

create table `event_schedule` (
  `id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `event_id` INT UNSIGNED NOT NULL,
  `venue` VARCHAR(255) NOT NULL,
  `map_coordinate` GEOMETRY NOT NULL,
  `state_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (event_id) REFERENCES event(id)
);

create table `event_artist` (
  `id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `event_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `position_no` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (event_id) REFERENCES event(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

create table `genre` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `parent_id` INT UNSIGNED NULL,
  `name` VARCHAR(255) NOT NULL,
  FOREIGN KEY (parent_id) REFERENCES genre(id)
);

# --- !Downs
drop table `user`;
drop table `event_status`;
drop table `event_location`;
drop table `event`;
drop table `photo`;
drop table `event_photo`;
drop table `event_schedule`;
drop table `event_artist`;
drop table `genre`;
