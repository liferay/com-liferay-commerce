create index IX_721C700D on CommerceAccount (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_462292EF on CommerceAccount (companyId, name[$COLUMN_LENGTH:75$]);

create index IX_2236CBA3 on CommerceAccountOrganizationRel (commerceAccountId);
create index IX_CBD2AA54 on CommerceAccountOrganizationRel (userId);

create index IX_2D10743B on CommerceAccountUserRel (commerceAccountId);
create index IX_7D6C70BC on CommerceAccountUserRel (userId);