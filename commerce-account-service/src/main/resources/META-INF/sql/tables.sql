create table CommerceAccount (
	externalReferenceCode VARCHAR(75) null,
	commerceAccountId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	parentCommerceAccountId LONG,
	logoId LONG,
	email VARCHAR(75) null,
	taxId VARCHAR(75) null,
	type_ INTEGER,
	active_ BOOLEAN,
	displayDate DATE null,
	expirationDate DATE null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table CommerceAccountOrganizationRel (
	commerceAccountId LONG not null,
	organizationId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (commerceAccountId, organizationId)
);

create table CommerceAccountUserRel (
	commerceAccountId LONG not null,
	commerceAccountUserId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (commerceAccountId, commerceAccountUserId)
);