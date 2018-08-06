create table CPDAvailabilityEstimate (
	uuid_ VARCHAR(75) null,
	CPDAvailabilityEstimateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionId LONG,
	commerceAvailabilityEstimateId LONG,
	lastPublishDate DATE null
);

create table CPDefinitionInventory (
	uuid_ VARCHAR(75) null,
	CPDefinitionInventoryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionId LONG,
	CPDefinitionInventoryEngine VARCHAR(75) null,
	lowStockActivity VARCHAR(75) null,
	displayAvailability BOOLEAN,
	displayStockQuantity BOOLEAN,
	minStockQuantity INTEGER,
	backOrders BOOLEAN,
	minOrderQuantity INTEGER,
	maxOrderQuantity INTEGER,
	allowedOrderQuantities VARCHAR(75) null,
	multipleOrderQuantity INTEGER
);

create table CommerceAddress (
	commerceAddressId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	name VARCHAR(75) null,
	description STRING null,
	street1 VARCHAR(75) null,
	street2 VARCHAR(75) null,
	street3 VARCHAR(75) null,
	city VARCHAR(75) null,
	zip VARCHAR(75) null,
	commerceRegionId LONG,
	commerceCountryId LONG,
	latitude DOUBLE,
	longitude DOUBLE,
	phoneNumber VARCHAR(75) null,
	defaultBilling BOOLEAN,
	defaultShipping BOOLEAN
);

create table CommerceAddressRestriction (
	commerceAddressRestrictionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	commerceCountryId LONG
);

create table CommerceAvailabilityEstimate (
	uuid_ VARCHAR(75) null,
	commerceAvailabilityEstimateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title STRING null,
	priority DOUBLE,
	lastPublishDate DATE null
);

create table CommerceCountry (
	uuid_ VARCHAR(75) null,
	commerceCountryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	billingAllowed BOOLEAN,
	shippingAllowed BOOLEAN,
	twoLettersISOCode VARCHAR(75) null,
	threeLettersISOCode VARCHAR(75) null,
	numericISOCode INTEGER,
	subjectToVAT BOOLEAN,
	priority DOUBLE,
	active_ BOOLEAN,
	lastPublishDate DATE null
);

create table CommerceOrder (
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceOrderId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	siteGroupId LONG,
	orderOrganizationId LONG,
	orderUserId LONG,
	commerceCurrencyId LONG,
	billingAddressId LONG,
	shippingAddressId LONG,
	commercePaymentMethodId LONG,
	commerceShippingMethodId LONG,
	shippingOptionName VARCHAR(255) null,
	purchaseOrderNumber VARCHAR(75) null,
	subtotal DECIMAL(30, 16) null,
	subtotalDiscountAmount DECIMAL(30, 16) null,
	subtotalDiscountPercentLevel1 DECIMAL(30, 16) null,
	subtotalDiscountPercentLevel2 DECIMAL(30, 16) null,
	subtotalDiscountPercentLevel3 DECIMAL(30, 16) null,
	subtotalDiscountPercentLevel4 DECIMAL(30, 16) null,
	shippingAmount DECIMAL(30, 16) null,
	shippingDiscountAmount DECIMAL(30, 16) null,
	shippingDiscountPercentLevel1 DECIMAL(30, 16) null,
	shippingDiscountPercentLevel2 DECIMAL(30, 16) null,
	shippingDiscountPercentLevel3 DECIMAL(30, 16) null,
	shippingDiscountPercentLevel4 DECIMAL(30, 16) null,
	taxAmount DECIMAL(30, 16) null,
	total DECIMAL(30, 16) null,
	totalDiscountAmount DECIMAL(30, 16) null,
	totalDiscountPercentageLevel1 DECIMAL(30, 16) null,
	totalDiscountPercentageLevel2 DECIMAL(30, 16) null,
	totalDiscountPercentageLevel3 DECIMAL(30, 16) null,
	totalDiscountPercentageLevel4 DECIMAL(30, 16) null,
	advanceStatus VARCHAR(75) null,
	paymentStatus INTEGER,
	orderStatus INTEGER,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table CommerceOrderItem (
	externalReferenceCode VARCHAR(75) null,
	commerceOrderItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderId LONG,
	CPInstanceId LONG,
	quantity INTEGER,
	shippedQuantity INTEGER,
	json TEXT null,
	name STRING null,
	sku VARCHAR(75) null,
	unitPrice DECIMAL(30, 16) null,
	discountAmount DECIMAL(30, 16) null,
	finalPrice DECIMAL(30, 16) null,
	discountPercentageLevel1 DECIMAL(30, 16) null,
	discountPercentageLevel2 DECIMAL(30, 16) null,
	discountPercentageLevel3 DECIMAL(30, 16) null,
	discountPercentageLevel4 DECIMAL(30, 16) null
);

create table CommerceOrderNote (
	externalReferenceCode VARCHAR(75) null,
	commerceOrderNoteId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderId LONG,
	content STRING null,
	restricted BOOLEAN
);

create table CommerceOrderPayment (
	commerceOrderPaymentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderId LONG,
	commercePaymentMethodId LONG,
	status INTEGER,
	content TEXT null
);

create table CommercePaymentMethod (
	commercePaymentMethodId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	imageId LONG,
	engineKey VARCHAR(75) null,
	priority DOUBLE,
	active_ BOOLEAN
);

create table CommerceRegion (
	uuid_ VARCHAR(75) null,
	commerceRegionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceCountryId LONG,
	name VARCHAR(75) null,
	code_ VARCHAR(75) null,
	priority DOUBLE,
	active_ BOOLEAN,
	lastPublishDate DATE null
);

create table CommerceShipment (
	commerceShipmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	siteGroupId LONG,
	shipmentOrganizationId LONG,
	shipmentUserId LONG,
	commerceAddressId LONG,
	commerceShippingMethodId LONG,
	shippingOptionName VARCHAR(75) null,
	carrier VARCHAR(75) null,
	trackingNumber VARCHAR(75) null,
	status INTEGER,
	shippingDate DATE null,
	expectedDate DATE null
);

create table CommerceShipmentItem (
	commerceShipmentItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceShipmentId LONG,
	commerceOrderItemId LONG,
	commerceWarehouseId LONG,
	quantity INTEGER
);

create table CommerceShippingMethod (
	commerceShippingMethodId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	imageId LONG,
	engineKey VARCHAR(75) null,
	priority DOUBLE,
	active_ BOOLEAN
);

create table CommerceWarehouse (
	commerceWarehouseId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	active_ BOOLEAN,
	street1 VARCHAR(75) null,
	street2 VARCHAR(75) null,
	street3 VARCHAR(75) null,
	city VARCHAR(75) null,
	zip VARCHAR(75) null,
	commerceRegionId LONG,
	commerceCountryId LONG,
	latitude DOUBLE,
	longitude DOUBLE,
	primary_ BOOLEAN
);

create table CommerceWarehouseItem (
	commerceWarehouseItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceWarehouseId LONG,
	CPInstanceId LONG,
	quantity INTEGER
);