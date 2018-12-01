create table History (
	uuid_ VARCHAR(75) null,
	historyId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	launchType VARCHAR(75) null,
	scheduledTaskId LONG,
	status INTEGER,
	startDate DATE null,
	endDate DATE null,
	executionType VARCHAR(75) null,
	errorLogFileEntryId LONG,
	runtimeLogFileEntryId LONG
);

create table Process (
	uuid_ VARCHAR(75) null,
	processId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	version VARCHAR(75) null,
	className VARCHAR(75) null,
	processType VARCHAR(75) null,
	contextPropertiesFileEntryId LONG,
	srcArchiveFileEntryId LONG
);

create table ScheduledTask (
	uuid_ VARCHAR(75) null,
	scheduledTaskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	frequency VARCHAR(75) null,
	processId LONG,
	status INTEGER,
	active_ BOOLEAN,
	runStartDate DATE null,
	runEndDate DATE null,
	startDate DATE null,
	startHour VARCHAR(75) null,
	enabled BOOLEAN
);