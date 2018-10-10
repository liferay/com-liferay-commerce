create table CommerceSubscriptionCycleEntry (
	uuid_ VARCHAR(75) null,
	commerceSubscriptionCycleEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceSubscriptionEntryId LONG,
	commerceOrderItemId LONG,
	renew BOOLEAN
);

create index IX_275B2AD7 on CommerceSubscriptionCycleEntry (commerceSubscriptionEntryId);
create unique index IX_DBBA76D5 on CommerceSubscriptionCycleEntry (commerceOrderItemId);
create index IX_3D29F0EE on CommerceSubscriptionCycleEntry (groupId);
create index IX_94A39230 on CommerceSubscriptionCycleEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F54AE4B2 on CommerceSubscriptionCycleEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

COMMIT_TRANSACTION;