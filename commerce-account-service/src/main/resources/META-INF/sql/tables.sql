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
	taxId VARCHAR(75) null,
	active_ BOOLEAN
);

create table CommerceAccountOrganizationRel (
	commerceAccountId LONG not null,
	userId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (commerceAccountId, userId)
);

create table CommerceAccountUserRel (
	commerceAccountId LONG not null,
	userId LONG not null,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (commerceAccountId, userId)
);