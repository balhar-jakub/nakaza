alter table nakaza_participant add column id_user int;
alter table nakaza_participant add constraint nakaza_participant_user_fk
  Foreign key (id_user) References nakaza_user(id);

alter table nakaza_participant add column description_public text;