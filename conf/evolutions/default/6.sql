# User schema

# --- !Ups
alter table account add column is_activated TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 after updated_at;

# --- !Downs
alter table account drop column is_activated;
