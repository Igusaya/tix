# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tix_user (
  id                        bigint not null,
  name                      varchar(255),
  mail                      varchar(255),
  pass                      varchar(255),
  icon                      varchar(255),
  createdate                timestamp not null,
  updatedate                timestamp not null,
  constraint pk_tix_user primary key (id))
;

create sequence tix_user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists tix_user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists tix_user_seq;

