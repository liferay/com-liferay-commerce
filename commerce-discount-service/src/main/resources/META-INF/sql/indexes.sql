create index IX_F7FFBCCA on CDiscountCAccountGroupRel (commerceAccountGroupId);
create index IX_1CA5EFB9 on CDiscountCAccountGroupRel (commerceDiscountId);

create index IX_E063D0AD on CommerceDiscount (companyId, couponCode[$COLUMN_LENGTH:75$]);
create index IX_122C15C4 on CommerceDiscount (displayDate, status);
create index IX_2FBF0739 on CommerceDiscount (expirationDate, status);
create index IX_687F1796 on CommerceDiscount (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_6B4EEC38 on CommerceDiscountRel (classNameId, classPK);
create index IX_DDFDEF40 on CommerceDiscountRel (commerceDiscountId, classNameId);

create index IX_CB9E6769 on CommerceDiscountRule (commerceDiscountId);