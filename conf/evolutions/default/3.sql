# User schema

# --- !Ups
create table `artist_photo` (
  `id` INT UNSIGNED NOT NULL PRIMARY KEY,
  `artist_id` INT UNSIGNED NOT NULL,
  `photo_id` INT UNSIGNED NOT NULL,
  `position_no` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  FOREIGN KEY (artist_id) REFERENCES user(id),
  FOREIGN KEY (photo_id) REFERENCES photo(id)
);

# --- !Downs
drop table `artist_photo`;
