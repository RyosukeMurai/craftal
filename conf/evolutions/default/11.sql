# User schema

# --- !Ups
alter table `artist` add column `about_inquiry` text not null;
alter table `artist` add column `home_page_url` varchar(255) not null;
alter table `artist` add column `shop_page_url` varchar(255) not null;
alter table `artist` add column `twitter_url` varchar(255) not null;
alter table `artist` add column `instagram_url` varchar(255) not null;
alter table `artist` add column `facebook_url` varchar(255) not null;
alter table `event_artist` add column `artist_comment` text not null;


# --- !Downs
alter table `artist` drop column `about_inquiry`;
alter table `artist` drop column `home_page_url`;
alter table `artist` drop column `shop_page_url`;
alter table `artist` drop column `twitter_url`;
alter table `artist` drop column `instagram_url`;
alter table `artist` drop column `facebook_url`;
alter table `event_artist` drop column `artist_comment`;