# User schema

# --- !Ups
ALTER TABLE `event_photo` DROP PRIMARY KEY, MODIFY COLUMN id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;

# --- !Downs
ALTER TABLE `event_photo` DROP PRIMARY KEY, MODIFY COLUMN id INT UNSIGNED NOT NULL PRIMARY KEY FIRST;

