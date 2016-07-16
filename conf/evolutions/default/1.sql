# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tix_user (
  id                        bigserial not null,
  name                      varchar(255),
  mail                      varchar(255),
  pass                      varchar(255),
  icon                      varchar(255),
  createdate                timestamp not null,
  updatedate                timestamp not null,
  constraint pk_tix_user primary key (id))
;




# --- !Downs

drop table if exists tix_user cascade;

