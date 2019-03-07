create table CommerceBatchEntry (
	commerceBatchEntryId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	key_ VARCHAR(75) null,
	name VARCHAR(75) null,
	startTime DATE null,
	endTime DATE null,
	status VARCHAR(75) null,
	callbackURL VARCHAR(75) null
);