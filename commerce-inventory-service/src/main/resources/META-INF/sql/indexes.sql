create index IX_57638B52 on CIAudit (sku[$COLUMN_LENGTH:75$]);

create index IX_4621C3A0 on CIBookedQuantity (expireDate);
create index IX_EC1719EE on CIBookedQuantity (sku[$COLUMN_LENGTH:75$]);

create index IX_F588314 on CIReplenishmentItem (availabilityDate);
create index IX_A9F25EBC on CIReplenishmentItem (commerceWarehouseId);
create index IX_6C8E22D3 on CIReplenishmentItem (sku[$COLUMN_LENGTH:75$], availabilityDate);

create index IX_8427A055 on CIWarehouse (active_, countryTwoLettersISOCode[$COLUMN_LENGTH:75$]);
create index IX_68E6B8D8 on CIWarehouse (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_7202EB2 on CIWarehouse (countryTwoLettersISOCode[$COLUMN_LENGTH:75$]);

create index IX_3996C237 on CIWarehouseGroupRel (groupId, commerceWarehouseId, primary_);
create index IX_E4C1F39E on CIWarehouseGroupRel (groupId, primary_);

create index IX_45E60710 on CIWarehouseItem (commerceWarehouseId, sku[$COLUMN_LENGTH:75$]);
create index IX_97CADC57 on CIWarehouseItem (sku[$COLUMN_LENGTH:75$]);