# User schema

# --- !Ups
alter table `user_auth` rename `user_identity`;
alter table `user_auth_password` rename `user_identity_password`;
alter table `user_auth_token` rename `user_identity_token`;

# --- !Downs
alter table `user_identity` rename `user_auth`;
alter table `user_identity_password` rename `user_identity`;
alter table `user_identity_token` rename `user_auth_token`;
