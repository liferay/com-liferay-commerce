import launcher from './entry.es';

import '../../styles/main.scss';

launcher('dataset-display', 'dataset-display', {
	apiUrl: '/o/headless-commerce-admin-order/v1.0/orders/37174/orderItems',
	bulkActions: [
		{
			action: '/edit',
			icon: 'plus',
			label: 'Add'
		},
		{
			action: '/delete',
			icon: 'trash',
			label: 'Delete',
			method: 'delete'
		}
	],
	currentPage: 1,
	fetchAtLoading: true,
	filters: [
		{
			label: 'Text test',
			operator: 'contains',
			slug: 'text-test',
			type: 'text',
			value: 'Test input'
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
			value: 123
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
	id: 'tableTest',
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
				label: 'ABS Sensor',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 4,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN93015',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min93015',
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
				label: 'Ball Joints',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 2,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN38794',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min38794',
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
				label: 'Bearings',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 1,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN00673',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min00673',
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
				label: 'Brake Pads',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 2,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN93018',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min93018',
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
				label: 'Brake Rotors',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 10,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN93020',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min93020',
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
				label: 'Bushings',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 2,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN38795',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min38795',
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
				label: 'Calipers',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 1,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN93021',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min93021',
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
				label: 'Cams',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 6,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN00674',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min00674',
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
				label: 'Coil Spring - Rear',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 6,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN38799',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min38799',
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
				label: 'CV Axles',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 7,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN38796',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min38796',
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
				label: 'CV Axles',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 12,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN55853',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min55853',
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
				label: 'Differential Ring and Pinion - Universal',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 50,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN38801',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min38801',
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
				label: 'Drive Shafts',
				url: '/form.html'
			},
			orderId: 37174,
			quantity: 1,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN55855',
				url: '/sidepanel-1.html'
			},
			skuExternalReferenceCode: 'min55855',
			skuId: 36744,
			subscription: false,
			unitPrice: 396
		}
	],
	pageSize: 5,
	paginationEntries: [
		{
			href:
				'http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=5',
			label: 5
		},
		{
			href:
				'http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=10',
			label: 10
		},
		{
			href:
				'http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=20',
			label: 20
		},
		{
			href:
				'http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=30',
			label: 30
		},
		{
			href:
				'http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=50',
			label: 50
		},
		{
			href:
				'http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=75',
			label: 75
		}
	],
	paginationSelectedEntry: 0,
	schema: {
		fields: [
			{
				contentRenderer: 'sidePanelLink',
				fieldName: 'sku',
				label: 'sku',
				sortable: false
			},
			{
				contentRenderer: 'modalLink',
				fieldName: 'name',
				label: 'name',
				sortable: false
			},
			{
				fieldName: 'unitPrice',
				label: 'price',
				sortable: false
			},
			{
				fieldName: 'discountAmount',
				label: 'discount',
				sortable: false
			},
			{
				fieldName: 'quantity',
				label: 'quantity',
				sortable: false
			},
			{
				fieldName: 'finalPrice',
				label: 'total',
				sortable: false
			}
		]
	},
	selectable: true,
	showPagination: true,
	sidePanelId: 'sidePanelTestId',
	spritemap: './assets/icons.svg',
	tableTitle: 'Orders',
	totalItems: 4
});
