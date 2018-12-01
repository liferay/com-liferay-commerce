create index IX_146E538F on History (companyId, scheduledTaskId);
create index IX_7AFD196 on History (groupId, status);
create index IX_8C191AAE on History (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6CE474B0 on History (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_8A698832 on Process (companyId, name[$COLUMN_LENGTH:75$]);
create index IX_FB3DD466 on Process (companyId, processType[$COLUMN_LENGTH:75$]);
create index IX_D24E1F5 on Process (groupId);
create index IX_EF69D909 on Process (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EA770DCB on Process (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_9D61EB11 on ScheduledTask (active_);
create index IX_97A489EF on ScheduledTask (companyId, name[$COLUMN_LENGTH:75$]);
create index IX_6B8B6A39 on ScheduledTask (enabled);
create index IX_73562697 on ScheduledTask (groupId, active_);
create index IX_417FA5BF on ScheduledTask (groupId, enabled);
create index IX_898F0EEC on ScheduledTask (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F326606E on ScheduledTask (uuid_[$COLUMN_LENGTH:75$], groupId);