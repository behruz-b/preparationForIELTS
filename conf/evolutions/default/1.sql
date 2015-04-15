# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "Marks" ("ID" SERIAL NOT NULL PRIMARY KEY,"NAME" VARCHAR(254) DEFAULT '' NOT NULL,"READING" DOUBLE PRECISION DEFAULT 0.0 NOT NULL,"LISTENING" DOUBLE PRECISION DEFAULT 0.0 NOT NULL,"WRITING" DOUBLE PRECISION DEFAULT 0.0 NOT NULL,"SPEAKING" DOUBLE PRECISION DEFAULT 0.0 NOT NULL,"TOTAL" DOUBLE PRECISION DEFAULT 0.0 NOT NULL);
create table "Rtext" ("ID" SERIAL NOT NULL PRIMARY KEY,"Title" VARCHAR(254) DEFAULT '' NOT NULL,"Text" VARCHAR(254) DEFAULT '' NOT NULL,"TEXTGROUP_ID" INTEGER NOT NULL);
create table "TEXTGROUPS" ("ID" SERIAL NOT NULL PRIMARY KEY,"NAME" VARCHAR(254) DEFAULT '' NOT NULL);
alter table "Rtext" add constraint "TEXT_FK_TEXTGROUP_ID" foreign key("TEXTGROUP_ID") references "TEXTGROUPS"("ID") on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table "Rtext" drop constraint "TEXT_FK_TEXTGROUP_ID";
drop table "TEXTGROUPS";
drop table "Rtext";
drop table "Marks";

