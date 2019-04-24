create index IX_2CEB77FC on CAccountGroupCAccountRel (commerceAccountGroupId);
create index IX_9808F83D on CAccountGroupCAccountRel (commerceAccountId);
create index IX_41A0DD5 on CAccountGroupCAccountRel (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);

create index IX_721C700D on CommerceAccount (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_FE5BDB63 on CommerceAccount (userId, type_);

create index IX_E487EEC8 on CommerceAccountGroup (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);

create index IX_2236CBA3 on CommerceAccountOrganizationRel (commerceAccountId);
create index IX_16599BBC on CommerceAccountOrganizationRel (organizationId);

create index IX_2D10743B on CommerceAccountUserRel (commerceAccountId);
create index IX_EC8DEFA6 on CommerceAccountUserRel (commerceAccountUserId);