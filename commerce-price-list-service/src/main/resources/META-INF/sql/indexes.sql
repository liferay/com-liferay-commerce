create unique index IX_85F53E15 on CPLUserSegmentEntryRel (commercePriceListId, commerceUserSegmentEntryId);
create index IX_A215DC0A on CPLUserSegmentEntryRel (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1A99DD0C on CPLUserSegmentEntryRel (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_56A1987C on CommercePriceEntry (CPInstanceUuid[$COLUMN_LENGTH:75$], commercePriceListId);
create unique index IX_2D76B43E on CommercePriceEntry (commercePriceListId, CPInstanceUuid[$COLUMN_LENGTH:75$]);
create index IX_B058565F on CommercePriceEntry (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_E185EB20 on CommercePriceEntry (groupId);
create index IX_1578F03E on CommercePriceEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F45C6E40 on CommercePriceEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_473B4D8D on CommercePriceList (commerceCurrencyId);
create index IX_328B5D27 on CommercePriceList (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_31913054 on CommercePriceList (displayDate, status);
create index IX_2C5B7A3E on CommercePriceList (groupId, status);
create index IX_863045BB on CommercePriceList (parentCommercePriceListId);
create index IX_FCE28706 on CommercePriceList (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_554D1708 on CommercePriceList (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_3DEE5A98 on CommercePriceListAccountRel (commerceAccountId, commercePriceListId);
create index IX_7279F379 on CommercePriceListAccountRel (commercePriceListId);
create index IX_D598A152 on CommercePriceListAccountRel (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E11D5454 on CommercePriceListAccountRel (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_A622C8AE on CommerceTierPriceEntry (commercePriceEntryId, minQuantity);
create index IX_95D59361 on CommerceTierPriceEntry (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_D78EDFDE on CommerceTierPriceEntry (groupId);
create index IX_B6C47140 on CommerceTierPriceEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_5D3847C2 on CommerceTierPriceEntry (uuid_[$COLUMN_LENGTH:75$], groupId);