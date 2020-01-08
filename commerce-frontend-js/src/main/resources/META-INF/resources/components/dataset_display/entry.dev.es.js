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
	creationMenuItems: [
		{
			href: "/standard/edit",
			label: 'Add'
		},
		{
			label: 'Add via modal',
			type: 'modal',
			url: 'modal/url'
		},
		{
			apiUrl: 'api/endpoint',
			editableFields: [
				'name',
				'quantity',
				'quantity'
			],
			label: 'Add via inline',
			type: 'inline',
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
		// {
		// 	id: 'product-name',
		// 	main: true,
		// 	placeholder: 'Product name',
		// 	value: 'Test input',
		// }
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
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 200,
			id: 37175,
			name: 'ABS Sensor',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'info',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 50
		},
		{
			bookedQuantityId: 0,
			comments: {
				name: "Square pls",
				quantity: "This is a test! I don't like this number btw",
			},
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 304,
			id: 37176,
			name: 'Ball Joints',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'secondary',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 152
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 70,
			id: 37177,
			name: 'Bearings',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 70
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 37.8,
			id: 37178,
			name: 'Brake Pads',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'success',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 21
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 400,
			id: 37197,
			name: 'Brake Rotors',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'warning',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 40
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 36,
			id: 37198,
			name: 'Bushings',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'danger',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 18
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 90,
			id: 37199,
			name: 'Calipers',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
			quantity: 1,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN93021',
				size: 'lg',
				url: '/sidepanel-1.html',
			},
			skuExternalReferenceCode: 'min93021',
			skuId: 35900,
			status: {
				displayStyle: 'success',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 90
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 4170,
			id: 37200,
			name: 'Cams',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
			quantity: 6,
			shippedQuantity: 0,
			shippingAddress: {},
			shippingAddressId: 0,
			sku: {
				label: 'MIN00674',
				size: 'sm',
				url: '/sidepanel-1.html',
			},
			skuExternalReferenceCode: 'min00674',
			skuId: 36132,
			status: {
				displayStyle: 'success',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 695
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 624,
			id: 37201,
			name: 'Coil Spring - Rear',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'success',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 104
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 623,
			id: 37202,
			name: 'CV Axles',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'success',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 89
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 1068,
			id: 37203,
			name: 'CV Axles',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'success',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 89
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 10550,
			id: 37204,
			name: 'Differential Ring and Pinion - Universal',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'success',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
			unitPrice: 211
		},
		{
			bookedQuantityId: 0,
			date: {
				icon: 'date',
				url: '/modal/date/url'
			},
			discountAmount: 0,
			discountPercentageLevel1: 0,
			discountPercentageLevel2: 0,
			discountPercentageLevel3: 0,
			discountPercentageLevel4: 0,
			finalPrice: 396,
			id: 37205,
			name: 'Drive Shafts',
			order: {
				label: '#37174',
				url: '/modal/order/url'
			},
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
			status: {
				displayStyle: 'success',
				label: 'delivered',
			},
			subscription: false,
			thumbnail: {
				alt: 'ABS Sensor',
				shape: 'rounded',
				size: 'lg',
				url: 'https://via.placeholder.com/150',
			},
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
				contentRenderer: 'picture',
				fieldName: 'thumbnail',
				label: '',
			},
			{
				contentRenderer: 'sidePanelLink',
				fieldName: 'sku',
				label: 'SKU',
				sortable: true,
			},
			{
				fieldName: 'name',
				label: 'Name',
				sortable: true
			},
			{
				fieldName: 'unitPrice',
				label: 'Price',
				sortable: true
			},
			{
				contentRenderer: 'modalLink',
				fieldName: 'order',
				label: 'Order',
			},
			{
				contentRenderer: 'label',
				fieldName: 'status',
				label: 'Status',
			},
			{
				fieldName: 'quantity',
				label: 'Quantity',
				sortable: true
			},
			{
				fieldName: 'finalPrice',
				label: 'Total',
				sortable: false
			},
			{
				contentRenderer: 'modalLink',
				fieldName: 'date',
			},
		]
	},
	showPagination: true,
	sidePanelId: 'sidePanelTestId',
	spritemap: './assets/icons.svg',
	stackedLayout: false,
}

const emailsDataSetDisplayProps = {
	activeContentRenderer: 'emails-list',
	apiUrl: 'http://localhost:8080/o/commerce-ui/commerce-data-set/20124/commerceOrderItems/commerceOrderItems?plid=1&portletId=com_liferay_commerce_order_web_internal_portlet_CommerceOrderPortlet&commerceOrderId=38938',
	creationMenuItems: [
		{
			href: "/standard/edit",
			label: 'Add'
		},
		{
			label: 'Add via modal',
			type: 'modal',
			url: 'modal/url'
		},
		{
			apiUrl: 'api/endpoint',
			editableFields: [
				'name',
				'quantity',
				'quantity'
			],
			label: 'Add via inline',
			type: 'inline',
		}
	],
	id: 'emailsDatasetDIsplay',
	items: [
		{
			abstract: "Pellentesque in ipsum id orci porta dapibus. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Nulla quis lorem ut libero malesuada feugiat. Pellentesque in ipsum id orci porta dapibus...",
			actionItems: [
				{
					href: "/delete/action/url",
					icon: "trash",
					label: "Delete",
				}
			],
			author: {
				avatarUrl: "https://via.placeholder.com/150",
				email: "john.doe@gmail.com",
				name: "John Doe",
			},
			date: "1 day ago",
			status: {
				displayStyle: 'danger',
				label: "Order not placed"
			},
			subject: "Mauris blandit aliquet elit, eget tincidunt nibh pulvinar.",
			url: "/side-panel/email.html",
		},
		{
			abstract: "Cras ultricies ligula sed magna dictum porta. Donec rutrum congue leo eget malesuada. Proin eget tortor risus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec velit neque, auctor sit amet aliquam vel, ullamcorper sit amet ligula. Quisque velit nisi, pretium ut lacinia in, elementum id enim...",
			actionItems: [
				{
					href: "/delete/action/url",
					icon: "trash",
					label: "Delete",
				}
			],
			author: {
				avatarUrl: "https://via.placeholder.com/150",
				email: "john.doe@gmail.com",
				name: "John Doe",
			},
			date: "14th April 2018",
			status: {
				displayStyle: 'success',
				label: "Order placed"
			},
			subject: "Curabitur aliquet quam id dui posuere blandit. Proin eget tortor risus.",
			url: "/side-panel/email.html",
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
	showPagination: true,
	sidePanelId: 'sidePanelTestId',
	spritemap: './assets/icons.svg'
}

datasetDisplayLauncher('dataset-display', 'dataset-display-root-id', dataSetDisplayProps);
datasetDisplayLauncher('emails-dataset-display', 'emails-dataset-display-root-id', emailsDataSetDisplayProps);

sidePanelLauncher('sidePanel', 'side-panel-root-id', {
	containerSelector: '.container',
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