import datasetDisplayLauncher from './entry.es';
import sidePanelLauncher from './../side_panel/entry.es';

import '../../styles/main.scss';

const dataSetDisplayProps = {
	apiUrl: 'http://localhost:8080/o/commerce-ui/commerce-data-set/20124/commerceOrderItems/commerceOrderItems?plid=1&portletId=com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet&commerceOrderId=38938',
	bulkActions: [
		{
			icon: 'plus',
			label: 'Add',
			sidePanelCompatible: true,
			url: '/side-panel/edit.html',
		},
		{
			icon: 'trash',
			label: 'Delete',
			method: 'delete',
			url: '/delete',
		}
	],
	filters: [
		{
			id: 'text-test',
			label: 'Text test',
			operator: 'eq',
			type: 'text',
			value: 'Test input'
		},
		{
			id: 'select-test',
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
			type: 'select',
			value: 'second-option'
		},
		{
			id: 'radio-test',
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
			type: 'radio'
		},
		{
			id: 'checkbox-test',
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
			operator: 'eq',
			type: 'checkbox',
			value: ['first-option', 'third-option']
		},
		{
			id: 'number-test',
			inputText: '$',
			label: 'Number test',
			max: 200,
			min: 20,
			operator: 'gt',
			type: 'number',
			value: 123
		},
		{
			id: 'product-name',
			main: true,
			placeholder: 'Product name',
			value: 'Test input',
		}
		// {
		// 	label: 'Date test',
		// 	operator: 'eq',
		// 	slug: 'date-test',
		// 	type: 'date',
		// 	value: {
		// 		day: 1,
		// 		month: 2,
		// 		year: 1
		// 	}
		// }
	],
	id: 'tableTest',
	items: [
		{
			actionItems: [
				{
					cssClasses: "",
					href: "http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet_javax.portlet.action=editCommerceOrderItem&_com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet_cmd=delete&_com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet_redirect=%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet_redirect%3Dhttp%253A%252F%252Flocalhost%253A8080%252Fgroup%252Fguest%252F%7E%252Fcontrol_panel%252Fmanage%253Fp_p_id%253Dcom_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_p_mode%253Dview%2526p_p_auth%253DVLNBjvR0%26_com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet_mvcRenderCommandName%3DeditCommerceOrder%26_com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet_commerceOrderId%3D38938%26p_p_auth%3DVLNBjvR0&_com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet_commerceOrderItemId=38943&p_p_auth=VLNBjvR0",
					icon: "trash",
					label: "Delete",
					onClick: "",
					order: 0,
					quickAction: false,
					separator: false,
				}
			],
			bookedQuantityId: 0,
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 200,
			id: 37175,
			// name: {
			// 	label: 'ABS Sensor',
			// 	url: '/form.html'
			// },
			name: 'ABS Sensor',
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
			// name: {
			// 	label: 'Ball Joints',
			// 	url: '/form.html'
			// },
			name: 'Ball Joints',
			orderId: 37174,
			quantity: 2,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN38794',
				url: '/sidepanel-1.html'
			},
			comments: {
				name: "Square pls",
				quantity: "This is a test! I don't like this number btw",
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
			// name: {
			// 	label: 'Bearings',
			// 	url: '/form.html'
			// },
			name: 'Bearings',
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
			// name: {
			// 	label: 'Brake Pads',
			// 	url: '/form.html'
			// },
			name: 'Brake Pads',
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
			// name: {
			// 	label: 'Brake Rotors',
			// 	url: '/form.html'
			// },
			name: 'Brake Rotors',
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
			// name: {
			// 	label: 'Bushings',
			// 	url: '/form.html'
			// },
			name: 'Bushings',
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
			// name: {
			// 	label: 'Calipers',
			// 	url: '/form.html'
			// },
			name: 'Calipers',
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
			// name: {
			// 	label: 'Cams',
			// 	url: '/form.html'
			// },
			name: 'Cams',
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
			// name: {
			// 	label: 'Coil Spring - Rear',
			// 	url: '/form.html'
			// },
			name: 'Coil Spring - Rear',
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
			// name: {
			// 	label: 'CV Axles',
			// 	url: '/form.html'
			// },
			name: 'CV Axles',
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
			// name: {
			// 	label: 'CV Axles',
			// 	url: '/form.html'
			// },
			name: 'CV Axles',
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
			// name: {
			// 	label: 'Differential Ring and Pinion - Universal',
			// 	url: '/form.html'
			// },
			name: 'Differential Ring and Pinion - Universal',
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
			// name: {
			// 	label: 'Drive Shafts',
			// 	url: '/form.html'
			// },
			name: 'Drive Shafts',
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
	pagination: {
		deltas: [
			{
				label: 5
			},
			{
				label: 10
			},
			{
				label: 20
			},
			{
				label: 30
			},
			{
				label: 50
			},
			{
				href:
					'http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=75',
				label: 75
			}
		],
		initialDelta: 10,
		initialPageNumber: 1,
		initialTotalItems: 40
	},
	schema: {
		fields: [
			{
				contentRenderer: 'sidePanelLink',
				fieldName: 'sku',
				label: 'sku',
				sortable: false
			},
			{
				fieldName: 'name',
				label: 'name',
				sortable: true
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
	showPagination: true,
	sidePanelId: 'sidePanelTestId',
	spritemap: './assets/icons.svg',
	tableTitle: 'Orders',
	totalItems: 4
}

// dataSetDisplayProps.items = [
// 	{
// 		id: 1,
// 		name: 'Ball Joints',
// 		sku: {
// 			label: 'MIN38794',
// 			url: '/sidepanel-1.html'
// 		},
// 		status: {
// 			label: 'Delivered',
// 			style: 'success'
// 		}
// 	}
// ];

// dataSetDisplayProps.schema.fields = [
// 	{
// 		contentRenderer: 'sidePanelLink',
// 		fieldName: 'sku',
// 		label: 'sku',
// 		sortable: false
// 	},
// 	{
// 		fieldName: 'name',
// 		label: 'name',
// 		sortable: false
// 	},
// 	{
// 		contentRenderer: 'label',
// 		fieldName: 'status',
// 		label: 'Status',
// 		sortable: false
// 	}
// ]

datasetDisplayLauncher('dataset-display', 'dataset-display-root-id', dataSetDisplayProps);

sidePanelLauncher('sidePanel', 'side-panel-root-id', {
	containerSelector: '.smart-table-wrapper',
	id: 'sidePanelTestId',
	items: [
		{
			href: '/side-panel/comments.html',
			icon: 'comments',
			slug: 'comments',
		},
		{
			href: '/side-panel/edit.html',
			icon: 'pencil',
			slug: 'edit',
		},
		{
			href: '/side-panel/changelog.html',
			icon: 'restore',
			slug: 'changelog',
		},
	],
	size: 'md',
	spritemap: './assets/icons.svg',
	topAnchorSelector: '.top-anchor',
});
