# User schema

# --- !Ups
insert into `genre` (id, name) values(1, 'アート・イラスト');
insert into `genre` (id, name) values(2, 'アクセサリー');
insert into `genre` (id, name) values(3, '衣類・布小物');
insert into `genre` (id, name) values(4, '雑貨・小物');
insert into `genre` (id, name) values(5, '陶器・陶芸');
insert into `genre` (id, name) values(6, '皮革工芸');
insert into `genre` (id, name) values(7, 'ガラス工芸');
insert into `genre` (id, name) values(8, '木工');
insert into `genre` (id, name) values(9, '食品');
insert into `genre` (id, name) values(10, 'グリーン');
insert into `genre` (id, name) values(11, 'その他');

# --- !Downs
delete from `genre` where `id` = 1;
delete from `genre` where `id` = 2;
delete from `genre` where `id` = 3;
delete from `genre` where `id` = 4;
delete from `genre` where `id` = 5;
delete from `genre` where `id` = 6;
delete from `genre` where `id` = 7;
delete from `genre` where `id` = 8;
delete from `genre` where `id` = 9;
delete from `genre` where `id` = 10;
delete from `genre` where `id` = 11;
