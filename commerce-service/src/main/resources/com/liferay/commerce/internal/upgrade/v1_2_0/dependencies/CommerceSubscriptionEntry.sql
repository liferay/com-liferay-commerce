create table CommerceSubscriptionEntry (
	uuid_ VARCHAR(75) null,
	commerceSubscriptionEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderItemId LONG,
	subscriptionCycleLength LONG,
	subscriptionCyclePeriod VARCHAR(75) null,
	maxSubscriptionCyclesNumber LONG,
	active_ BOOLEAN,
	nextIterationDate DATE null
);

create index IX_4472109B on CommerceSubscriptionEntry (active_);
create index IX_53297276 on CommerceSubscriptionEntry (groupId, userId);
create index IX_216F84A2 on CommerceSubscriptionEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C4F4CBA4 on CommerceSubscriptionEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

COMMIT_TRANSACTION;