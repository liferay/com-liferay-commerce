create index IX_C671CBD3 on CommerceCurrency (companyId, active_);
create index IX_ADF54822 on CommerceCurrency (companyId, primary_, active_);
create unique index IX_1C42BF4A on CommerceCurrency (groupId, code_[$COLUMN_LENGTH:75$]);
create index IX_7C490A66 on CommerceCurrency (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_113CF268 on CommerceCurrency (uuid_[$COLUMN_LENGTH:75$], groupId);