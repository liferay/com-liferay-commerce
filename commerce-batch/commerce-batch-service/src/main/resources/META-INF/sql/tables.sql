create table CommerceBatchJobExecution (
	commerceBatchJobExecutionId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	commerceBatchJobInstanceId LONG,
	startTime DATE null,
	endTime DATE null,
	status VARCHAR(75) null,
	exitCode VARCHAR(75) null,
	exitMessage VARCHAR(75) null
);

create table CommerceBatchJobInstance (
	commerceBatchJobInstanceId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	batchJobName VARCHAR(75) null,
	key_ VARCHAR(75) null
);