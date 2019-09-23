create unique index IX_17D56F1B on CPDAvailabilityEstimate (CProductId);
create index IX_E560850D on CPDAvailabilityEstimate (commerceAvailabilityEstimateId);
create index IX_609B2AF4 on CPDAvailabilityEstimate (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_34D62DF1 on CPDefinitionInventory (CPDefinitionId);
create index IX_51AED1D6 on CPDefinitionInventory (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_274DD5D8 on CPDefinitionInventory (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_15EA4714 on CommerceAddress (classNameId, classPK);
create index IX_CD76FE87 on CommerceAddress (commerceCountryId);
create index IX_71C5A9DD on CommerceAddress (commerceRegionId);
create index IX_6D0EADB7 on CommerceAddress (companyId, classNameId, classPK, type_);
create index IX_EEACF18E on CommerceAddress (groupId, classNameId, classPK, defaultBilling);
create index IX_333246DF on CommerceAddress (groupId, classNameId, classPK, defaultShipping);

create unique index IX_495311F8 on CommerceAddressRestriction (classNameId, classPK, commerceCountryId);
create index IX_69DBF5AD on CommerceAddressRestriction (commerceCountryId);

create index IX_72527224 on CommerceAvailabilityEstimate (companyId);
create index IX_EA65A078 on CommerceAvailabilityEstimate (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_F8360682 on CommerceCountry (companyId, active_);
create index IX_9DE350C1 on CommerceCountry (companyId, billingAllowed, active_);
create unique index IX_7C7152E0 on CommerceCountry (companyId, numericISOCode);
create index IX_570D4D26 on CommerceCountry (companyId, shippingAllowed, active_);
create unique index IX_415D595A on CommerceCountry (companyId, twoLettersISOCode[$COLUMN_LENGTH:75$]);
create index IX_91EA24D5 on CommerceCountry (uuid_[$COLUMN_LENGTH:75$], companyId);

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

create index IX_68FBA2B5 on CommerceShipment (groupId, status);

create index IX_9FE20732 on CommerceShipmentItem (commerceShipmentId);
create index IX_DB0BB83C on CommerceShipmentItem (groupId);

create index IX_42E5F6EF on CommerceShippingMethod (groupId, active_);
create unique index IX_C4557F93 on CommerceShippingMethod (groupId, engineKey[$COLUMN_LENGTH:75$]);

create unique index IX_BE881965 on CommerceSubscriptionEntry (CPInstanceUuid[$COLUMN_LENGTH:75$], CProductId, commerceOrderItemId);
create index IX_43E6F382 on CommerceSubscriptionEntry (companyId, userId);
create index IX_B99DE058 on CommerceSubscriptionEntry (groupId, companyId, userId);
create index IX_6D080A04 on CommerceSubscriptionEntry (groupId, userId);
create index IX_B496E103 on CommerceSubscriptionEntry (subscriptionStatus);
create index IX_4363DED4 on CommerceSubscriptionEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_943E0A56 on CommerceSubscriptionEntry (uuid_[$COLUMN_LENGTH:75$], groupId);