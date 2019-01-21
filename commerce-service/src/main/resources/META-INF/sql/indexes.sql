create unique index IX_17D56F1B on CPDAvailabilityEstimate (CProductId);
create index IX_E560850D on CPDAvailabilityEstimate (commerceAvailabilityEstimateId);
create index IX_609B2AF4 on CPDAvailabilityEstimate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7919DE76 on CPDAvailabilityEstimate (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_34D62DF1 on CPDefinitionInventory (CPDefinitionId);
create index IX_51AED1D6 on CPDefinitionInventory (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_274DD5D8 on CPDefinitionInventory (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_B1B042BF on CSubscriptionCycleEntry (commerceOrderItemId);
create index IX_7A4C6193 on CSubscriptionCycleEntry (commerceSubscriptionEntryId);
create index IX_37D2CBD8 on CSubscriptionCycleEntry (groupId);
create index IX_EC715E86 on CSubscriptionCycleEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E1ABCE88 on CSubscriptionCycleEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_15EA4714 on CommerceAddress (classNameId, classPK);
create index IX_CD76FE87 on CommerceAddress (commerceCountryId);
create index IX_71C5A9DD on CommerceAddress (commerceRegionId);
create index IX_EEACF18E on CommerceAddress (groupId, classNameId, classPK, defaultBilling);
create index IX_333246DF on CommerceAddress (groupId, classNameId, classPK, defaultShipping);

create unique index IX_495311F8 on CommerceAddressRestriction (classNameId, classPK, commerceCountryId);
create index IX_69DBF5AD on CommerceAddressRestriction (commerceCountryId);

create index IX_9D5931A6 on CommerceAvailabilityEstimate (groupId);
create index IX_EA65A078 on CommerceAvailabilityEstimate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C74BE4FA on CommerceAvailabilityEstimate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7BB13C80 on CommerceCountry (groupId, active_);
create index IX_FEDECABF on CommerceCountry (groupId, billingAllowed, active_);
create unique index IX_D84B0322 on CommerceCountry (groupId, numericISOCode);
create index IX_158112E8 on CommerceCountry (groupId, shippingAllowed, active_);
create unique index IX_FB03A9D8 on CommerceCountry (groupId, twoLettersISOCode[$COLUMN_LENGTH:75$]);
create index IX_91EA24D5 on CommerceCountry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7EFDC97 on CommerceCountry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_12131FC1 on CommerceOrder (billingAddressId);
create index IX_7DD246EA on CommerceOrder (commerceAccountId, groupId, orderStatus);
create index IX_81097E4C on CommerceOrder (commerceAccountId, orderStatus);
create index IX_48EEEDEE on CommerceOrder (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_4F4CAEE4 on CommerceOrder (groupId, commerceAccountId, orderStatus);
create index IX_67E0AF05 on CommerceOrder (groupId, userId, orderStatus);
create index IX_4B11FAD8 on CommerceOrder (shippingAddressId);
create index IX_75679B1F on CommerceOrder (userId, createDate, orderStatus);
create index IX_5AF685CD on CommerceOrder (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_58101B8F on CommerceOrder (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2E1BB39D on CommerceOrderItem (CPInstanceId);
create index IX_F9E8D927 on CommerceOrderItem (CProductId);
create index IX_415AF3E3 on CommerceOrderItem (commerceOrderId, CPInstanceId);
create index IX_15B37023 on CommerceOrderItem (commerceOrderId, subscription);
create index IX_12257E21 on CommerceOrderItem (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);

create index IX_CEB86C22 on CommerceOrderNote (commerceOrderId, restricted);
create index IX_EF4EEF80 on CommerceOrderNote (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);

create index IX_CF274005 on CommerceOrderPayment (commerceOrderId);

create index IX_49C93338 on CommerceRegion (commerceCountryId, active_);
create unique index IX_2D8BEE31 on CommerceRegion (commerceCountryId, code_[$COLUMN_LENGTH:75$]);
create index IX_3BC85C89 on CommerceRegion (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DBA0714B on CommerceRegion (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_68FBA2B5 on CommerceShipment (groupId, status);

create index IX_9FE20732 on CommerceShipmentItem (commerceShipmentId);
create index IX_DB0BB83C on CommerceShipmentItem (groupId);

create index IX_42E5F6EF on CommerceShippingMethod (groupId, active_);
create unique index IX_C4557F93 on CommerceShippingMethod (groupId, engineKey[$COLUMN_LENGTH:75$]);

create unique index IX_BE881965 on CommerceSubscriptionEntry (CPInstanceUuid[$COLUMN_LENGTH:75$], CProductId, commerceOrderItemId);
create index IX_6D080A04 on CommerceSubscriptionEntry (groupId, userId);
create index IX_B496E103 on CommerceSubscriptionEntry (subscriptionStatus);
create index IX_4363DED4 on CommerceSubscriptionEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_943E0A56 on CommerceSubscriptionEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_81487FD9 on CommerceWarehouse (groupId, active_, commerceCountryId);
create index IX_4500A0CA on CommerceWarehouse (groupId, commerceCountryId);
create index IX_5F7D8BED on CommerceWarehouse (groupId, primary_);

create index IX_BAE6B80D on CommerceWarehouseItem (CProductId, CPInstanceUuid[$COLUMN_LENGTH:75$]);
create unique index IX_AFA32863 on CommerceWarehouseItem (commerceWarehouseId, CPInstanceUuid[$COLUMN_LENGTH:75$]);