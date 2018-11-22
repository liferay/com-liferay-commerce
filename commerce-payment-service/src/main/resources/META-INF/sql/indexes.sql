create index IX_98EF79EB on CommercePaymentMethodGroupRel (groupId, active_);
create unique index IX_BE0A338F on CommercePaymentMethodGroupRel (groupId, engineKey[$COLUMN_LENGTH:75$]);

create index IX_669E18ED on Commerce_CommercePaymentMethod (groupId, active_);
create unique index IX_DA8D1A11 on Commerce_CommercePaymentMethod (groupId, engineKey[$COLUMN_LENGTH:75$]);