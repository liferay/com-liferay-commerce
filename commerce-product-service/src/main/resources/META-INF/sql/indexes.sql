create index IX_B2AFFCE5 on CPAttachmentFileEntry (classNameId, classPK, displayDate, status);
create unique index IX_DD114140 on CPAttachmentFileEntry (classNameId, classPK, fileEntryId);
create index IX_A6E0353A on CPAttachmentFileEntry (classNameId, classPK, type_, status);
create index IX_59F57821 on CPAttachmentFileEntry (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_A0B4C71A on CPAttachmentFileEntry (displayDate, status);
create index IX_C2C5D600 on CPAttachmentFileEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BFCBDC82 on CPAttachmentFileEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_EDD77D36 on CPDSpecificationOptionValue (CPDefinitionId, CPDSpecificationOptionValueId);
create index IX_95975FB4 on CPDSpecificationOptionValue (CPDefinitionId, CPOptionCategoryId);
create index IX_173E8E91 on CPDSpecificationOptionValue (CPDefinitionId, CPSpecificationOptionId);
create index IX_4F4EDBA5 on CPDSpecificationOptionValue (CPOptionCategoryId);
create index IX_573BE140 on CPDSpecificationOptionValue (CPSpecificationOptionId);
create index IX_8DA57014 on CPDSpecificationOptionValue (groupId);
create index IX_508DBDCA on CPDSpecificationOptionValue (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_551F2ECC on CPDSpecificationOptionValue (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3D5A0021 on CPDefinition (CPTaxCategoryId);
create index IX_1F4B9C67 on CPDefinition (CProductId, status);
create index IX_217AF702 on CPDefinition (companyId);
create index IX_A465D100 on CPDefinition (displayDate, status);
create index IX_419350EA on CPDefinition (groupId, status);
create index IX_8EA585DA on CPDefinition (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BA9BADC on CPDefinition (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_7FA4A6B on CPDefinitionLink (CPDefinitionId, CProductId, type_[$COLUMN_LENGTH:75$]);
create index IX_5572A666 on CPDefinitionLink (CPDefinitionId, type_[$COLUMN_LENGTH:75$]);
create index IX_F7B5F85A on CPDefinitionLink (CProductId, type_[$COLUMN_LENGTH:75$]);
create index IX_220CC8F4 on CPDefinitionLink (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_865AFC76 on CPDefinitionLink (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_F4B4ACB5 on CPDefinitionLocalization (CPDefinitionId, languageId[$COLUMN_LENGTH:75$]);

create unique index IX_E57A1C2A on CPDefinitionOptionRel (CPDefinitionId, CPOptionId);
create index IX_749E99EB on CPDefinitionOptionRel (CPDefinitionId, skuContributor);
create index IX_449BFCFE on CPDefinitionOptionRel (companyId);
create index IX_A65BAB00 on CPDefinitionOptionRel (groupId);
create index IX_7BED0C5E on CPDefinitionOptionRel (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EB691260 on CPDefinitionOptionRel (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_8FDF08C0 on CPDefinitionOptionValueRel (CPDefinitionOptionRelId, key_[$COLUMN_LENGTH:75$]);
create index IX_44C2E505 on CPDefinitionOptionValueRel (companyId);
create index IX_695AE8C7 on CPDefinitionOptionValueRel (groupId);
create index IX_2434CAD7 on CPDefinitionOptionValueRel (key_[$COLUMN_LENGTH:75$]);
create index IX_CD95E77 on CPDefinitionOptionValueRel (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_34516B9 on CPDefinitionOptionValueRel (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_290BF7BA on CPDisplayLayout (classNameId, classPK);
create index IX_71FB6E49 on CPDisplayLayout (groupId, classNameId);
create index IX_EEFA81D9 on CPDisplayLayout (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_68BBAA9B on CPDisplayLayout (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BA2E8536 on CPFriendlyURLEntry (classNameId, classPK);
create index IX_EA0848B2 on CPFriendlyURLEntry (groupId, classNameId, classPK, languageId[$COLUMN_LENGTH:75$], main);
create index IX_6A1505A2 on CPFriendlyURLEntry (groupId, classNameId, classPK, languageId[$COLUMN_LENGTH:75$], urlTitle[$COLUMN_LENGTH:255$]);
create index IX_3F4546AB on CPFriendlyURLEntry (groupId, classNameId, classPK, main);
create index IX_7A386C69 on CPFriendlyURLEntry (groupId, classNameId, languageId[$COLUMN_LENGTH:75$], urlTitle[$COLUMN_LENGTH:255$]);
create index IX_496A7862 on CPFriendlyURLEntry (groupId, classNameId, urlTitle[$COLUMN_LENGTH:255$]);
create index IX_BD972D55 on CPFriendlyURLEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F4450517 on CPFriendlyURLEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_25BEB828 on CPInstance (CPDefinitionId, CPInstanceUuid[$COLUMN_LENGTH:75$]);
create index IX_C399720F on CPInstance (CPDefinitionId, displayDate, status);
create unique index IX_7E830576 on CPInstance (CPDefinitionId, sku[$COLUMN_LENGTH:75$]);
create index IX_F4C9CDD on CPInstance (CPDefinitionId, status);
create index IX_34763899 on CPInstance (CPInstanceUuid[$COLUMN_LENGTH:75$]);
create index IX_E2C3A97D on CPInstance (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_7C65903E on CPInstance (displayDate, status);
create index IX_FF605F28 on CPInstance (groupId, status);
create index IX_8A7A3F5C on CPInstance (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F902ECDE on CPInstance (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_BCD82992 on CPMeasurementUnit (companyId, key_[$COLUMN_LENGTH:75$], type_);
create index IX_107489F5 on CPMeasurementUnit (companyId, primary_, type_);
create index IX_1C2742A6 on CPMeasurementUnit (companyId, type_);
create index IX_E244DBED on CPMeasurementUnit (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_753D79AF on CPMeasurementUnit (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_25450C9D on CPOption (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_BDFC3674 on CPOption (companyId, key_[$COLUMN_LENGTH:75$]);
create index IX_C565E27C on CPOption (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_33CA3696 on CPOptionCategory (companyId, key_[$COLUMN_LENGTH:75$]);
create index IX_957E69A on CPOptionCategory (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_F4C57C5A on CPOptionValue (CPOptionId, key_[$COLUMN_LENGTH:75$]);
create index IX_5547362A on CPOptionValue (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_17FEC609 on CPOptionValue (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_421ED80 on CPSpecificationOption (CPOptionCategoryId);
create unique index IX_1DA76D6B on CPSpecificationOption (companyId, key_[$COLUMN_LENGTH:75$]);
create index IX_5B218A65 on CPSpecificationOption (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_64046706 on CPTaxCategory (companyId);

create index IX_7BB74B87 on CProduct (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_77F5B8F8 on CProduct (groupId);
create index IX_39773566 on CProduct (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_9F9DD68 on CProduct (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_1198BFF9 on CommerceCatalog (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_3D14D2A7 on CommerceCatalog (companyId, system);

create index IX_690E2FE3 on CommerceChannel (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_E1ECD95 on CommerceChannel (siteGroupId);

create unique index IX_3B0D3DC7 on CommerceChannelRel (classNameId, classPK, commerceChannelId);
create index IX_48F8F6FC on CommerceChannelRel (commerceChannelId);