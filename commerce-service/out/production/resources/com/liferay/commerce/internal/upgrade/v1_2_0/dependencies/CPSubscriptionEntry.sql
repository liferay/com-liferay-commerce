create table CPSubscriptionEntry (
	uuid_ VARCHAR(75) null,
	CPSubscriptionEntryId LONG not null primary key,
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

create index IX_4472109B on CPSubscriptionEntry (active_);
create index IX_53297276 on CPSubscriptionEntry (groupId, userId);
create index IX_216F84A2 on CPSubscriptionEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C4F4CBA4 on CPSubscriptionEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

COMMIT_TRANSACTION;