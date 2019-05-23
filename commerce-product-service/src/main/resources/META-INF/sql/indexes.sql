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
create index IX_E2C3A97D on CPInstance (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_7C65903E on CPInstance (displayDate, status);
create index IX_FF605F28 on CPInstance (groupId, status);
create index IX_8A7A3F5C on CPInstance (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F902ECDE on CPInstance (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_FC5A9690 on CPMeasurementUnit (groupId, key_[$COLUMN_LENGTH:75$], type_);
create index IX_BEDC47F3 on CPMeasurementUnit (groupId, primary_, type_);
create index IX_1546C624 on CPMeasurementUnit (groupId, type_);
create index IX_E244DBED on CPMeasurementUnit (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_753D79AF on CPMeasurementUnit (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_25450C9D on CPOption (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create unique index IX_5DD6FB76 on CPOption (groupId, key_[$COLUMN_LENGTH:75$]);
create index IX_C565E27C on CPOption (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BABCD7FE on CPOption (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_88DFEE42 on CPOptionCategory (companyId);
create unique index IX_120A9C18 on CPOptionCategory (groupId, key_[$COLUMN_LENGTH:75$]);
create index IX_957E69A on CPOptionCategory (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8CFD4B9C on CPOptionCategory (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_F4C57C5A on CPOptionValue (CPOptionId, key_[$COLUMN_LENGTH:75$]);
create index IX_5547362A on CPOptionValue (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_F5E154F5 on CPOptionValue (groupId);
create index IX_17FEC609 on CPOptionValue (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1D633ACB on CPOptionValue (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_B4B2EAFB on CPRule (groupId);

create index IX_5AF83912 on CPRuleAssetCategoryRel (CPRuleId);
create index IX_D8DFC5F on CPRuleAssetCategoryRel (assetCategoryId);

create index IX_EADE9B38 on CPRuleUserSegmentRel (CPRuleId);
create index IX_617DFD14 on CPRuleUserSegmentRel (commerceUserSegmentEntryId);

create index IX_421ED80 on CPSpecificationOption (CPOptionCategoryId);
create unique index IX_1E01842D on CPSpecificationOption (groupId, key_[$COLUMN_LENGTH:75$]);
create index IX_5B218A65 on CPSpecificationOption (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FFE36627 on CPSpecificationOption (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DB66F708 on CPTaxCategory (groupId);

create index IX_7BB74B87 on CProduct (companyId, externalReferenceCode[$COLUMN_LENGTH:75$]);
create index IX_77F5B8F8 on CProduct (groupId);
create index IX_39773566 on CProduct (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_9F9DD68 on CProduct (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_7CD290C4 on CommerceCatalog (companyId);