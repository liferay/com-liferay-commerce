import launcher from './entry.es';

window.Table = launcher('table', 'table', {
	filters: [
		{
			label: 'Text test',
			operator: 'contains',
			slug: 'text-test',
			type: 'text',
			value: 'Test input',
		},
		{
			items: [
				{
					label: 'First option',
					value: 'first-option'
				},
				{
					label: 'Second option',
					value: 'second-option'
				}
			],
			label: 'Select test',
			operator: 'eq',
			slug: 'select-test',
			type: 'select',
			value: 'second-option'
		},
		{
			items: [
				{
					label: 'First option',
					value: 'first-option'
				},
				{
					label: 'Second option',
					value: 'second-option'
				}
			],
			label: 'Radio test',
			operator: 'eq',
			slug: 'radio-test',
			type: 'radio'
		},
		{
			items: [
				{
					label: 'First option',
					value: 'first-option'
				},
				{
					label: 'Second option',
					value: 'second-option'
				},
				{
					label: 'Third option',
					value: 'third-option'
				}
			],
			label: 'Checkbox test',
			operator: 'contains',
			slug: 'checkbox-test',
			type: 'checkbox',
			value: ['first-option', 'third-option']
		},
		{
			inputText: '$',
			label: 'Number test',
			max: 200,
			min: 20,
			operator: 'eq',
			slug: 'number-test',
			type: 'number',
			value: 123,
		},
		{
			label: 'Date test',
			operator: 'eq',
			slug: 'date-test',
			type: 'date',
			value: {
				day: 1,
				month: 2,
				year: 1
			}
		}
	],
	items: [
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 200,
			id: 37175,
			name: {
				en: "ABS Sensor"
			},
			orderId: 37174,
			quantity: 4,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN93015",
			skuExternalReferenceCode: "min93015",
			skuId: 35663,
			subscription: false,
			unitPrice: 50
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 304,
			id: 37176,
			name: {
				en: "Ball Joints"
			},
			orderId: 37174,
			quantity: 2,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN38794",
			skuExternalReferenceCode: "min38794",
			skuId: 36456,
			subscription: false,
			unitPrice: 152
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 70,
			id: 37177,
			name: {
				en: "Bearings"
			},
			orderId: 37174,
			quantity: 1,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN00673",
			skuExternalReferenceCode: "min00673",
			skuId: 36114,
			subscription: false,
			unitPrice: 70
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 37.8,
			id: 37178,
			name: {
				en: "Brake Pads"
			},
			orderId: 37174,
			quantity: 2,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN93018",
			skuExternalReferenceCode: "min93018",
			skuId: 35798,
			subscription: false,
			unitPrice: 21
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 400,
			id: 37197,
			name: {
				en: "Brake Rotors"
			},
			orderId: 37174,
			quantity: 10,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN93020",
			skuExternalReferenceCode: "min93020",
			skuId: 35872,
			subscription: false,
			unitPrice: 40
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 36,
			id: 37198,
			name: {
				en: "Bushings"
			},
			orderId: 37174,
			quantity: 2,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN38795",
			skuExternalReferenceCode: "min38795",
			skuId: 36474,
			subscription: false,
			unitPrice: 18
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 90,
			id: 37199,
			name: {
				en: "Calipers"
			},
			orderId: 37174,
			quantity: 1,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN93021",
			skuExternalReferenceCode: "min93021",
			skuId: 35900,
			subscription: false,
			unitPrice: 90
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 4170,
			id: 37200,
			name: {
				en: "Cams"
			},
			orderId: 37174,
			quantity: 6,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN00674",
			skuExternalReferenceCode: "min00674",
			skuId: 36132,
			subscription: false,
			unitPrice: 695
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 624,
			id: 37201,
			name: {
				en: "Coil Spring - Rear"
			},
			orderId: 37174,
			quantity: 6,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN38799",
			skuExternalReferenceCode: "min38799",
			skuId: 36553,
			subscription: false,
			unitPrice: 104
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 623,
			id: 37202,
			name: {
				en: "CV Axles"
			},
			orderId: 37174,
			quantity: 7,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN38796",
			skuExternalReferenceCode: "min38796",
			skuId: 36492,
			subscription: false,
			unitPrice: 89
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 1068,
			id: 37203,
			name: {
				en: "CV Axles"
			},
			orderId: 37174,
			quantity: 12,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN55853",
			skuExternalReferenceCode: "min55853",
			skuId: 36700,
			subscription: false,
			unitPrice: 89
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 10550,
			id: 37204,
			name: {
				en: "Differential Ring and Pinion - Universal"
			},
			orderId: 37174,
			quantity: 50,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN38801",
			skuExternalReferenceCode: "min38801",
			skuId: 36604,
			subscription: false,
			unitPrice: 211
		},
		{
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 396,
			id: 37205,
			name: {
				en: "Drive Shafts"
			},
			orderId: 37174,
			quantity: 1,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: "MIN55855",
			skuExternalReferenceCode: "min55855",
			skuId: 36744,
			subscription: false,
			unitPrice: 396
		}
	],
	schema: {fields:[
		{
			fieldName:"sku",
			label:"sku",
			sortable:false
		},
		{
			fieldName:"name",
			label:"name",
			sortable:false
		},
		{
			fieldName:"unitPrice",
			// contentRenderer:"commerceTablePrice",
			label:"price",
			sortable:false
		},
		{
			fieldName:"discountAmount",
			label:"discount",
			sortable:false
		},
		{
			fieldName:"quantity",
			label:"quantity",
			sortable:false
		},
		{
			fieldName:"finalPrice",
			label:"total",
			sortable:false
		}
	]},
	spritemap: './assets/icons.svg',
	summaryItems: [
		{
			label: 'Items Subtotal',
			value: '$ 2,208.50'
		},
		{
			label: 'Order Discount',
			value: '$ 0.00'
		},
		{
			label: 'Promotion codes',
			value: '--'
		},
		{
			label: 'Estimated Tax',
			value: '$ 0.00'
		},
		{
			label: 'Shipping & Handing',
			value: '$ 50.00'
		},
		{
			label: 'Grand Total',
			style: 'big',
			value: '$ 2,258.50',
		},
		{
			style: 'divider'
		},
		{
			label: 'Total return refounds',
			style: 'danger',
			value: '$ 0.00'
		},
		{
			label: 'Total appeasement refounds',
			style: 'danger',
			value: '$ 0.00'
		}
	],
	apiUrl: '/o/headless-commerce-admin-order/v1.0/orders/37174/orderItems',
	currentPage: 1,
	fetchAtLoading: true,
	pageSize: 5,
	paginationEntries: [{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=5",label:5},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=10",label:10},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=20",label:20},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=30",label:30},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=50",label:50},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=75",label:75}],
	paginationSelectedEntry: 0,
	selectable: true,
	showPagination: true,
	summaryName: 'Table summary',
	// tableName: 'Orders',
	totalItems: 4,
});
