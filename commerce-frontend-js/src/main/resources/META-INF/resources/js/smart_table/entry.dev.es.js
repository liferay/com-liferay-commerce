import launcher from './entry.es';

window.Table = launcher('table', 'table', {
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
	items: [{thumbnail:"https://next.clayui.com/images/long_user_image.png",quantity:4,actionItems:[{icon:"",href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=1&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_javax.portlet.action=editCommerceOrderItem&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_cmd=delete&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_redirect=%2Fgroup%2Ftest-1%2Fpending-orders%3Fp_p_id%3Dcom_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet%26p_p_lifecycle%3D0%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName%3DeditCommerceOrder%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderId%3D37174%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_backURL%3D%252Fgroup%252Ftest-1%252Fpending-orders&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderItemId=37175&p_auth=NrI5slwn",label:"Delete",quickAction:false,separator:false,order:0}],orderId:37174,orderItemId:37175,discount:"",viewShipmentsURL:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=pop_up&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName=viewCommerceOrderShipments&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderItemId=37174&p_r_p_backURL=%2Fgroup%2Ftest-1%2Fpending-orders%3Fp_p_id%3Dcom_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet%26p_p_lifecycle%3D0%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName%3DeditCommerceOrder%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderId%3D37174%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_backURL%3D%252Fgroup%252Ftest-1%252Fpending-orders",promoPrice:"",total:"$200.00",price:"$50.00",name:"ABS Sensor",shippedQuantity:0,sku:"MIN93015"},{thumbnail:"https://next.clayui.com/images/long_user_image.png",quantity:2,actionItems:[{icon:"",href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=1&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_javax.portlet.action=editCommerceOrderItem&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_cmd=delete&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_redirect=%2Fgroup%2Ftest-1%2Fpending-orders%3Fp_p_id%3Dcom_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet%26p_p_lifecycle%3D0%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName%3DeditCommerceOrder%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderId%3D37174%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_backURL%3D%252Fgroup%252Ftest-1%252Fpending-orders&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderItemId=37176&p_auth=NrI5slwn",label:"Delete",quickAction:false,separator:false,order:0}],orderId:37174,orderItemId:37176,discount:"",viewShipmentsURL:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=pop_up&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName=viewCommerceOrderShipments&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderItemId=37174&p_r_p_backURL=%2Fgroup%2Ftest-1%2Fpending-orders%3Fp_p_id%3Dcom_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet%26p_p_lifecycle%3D0%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName%3DeditCommerceOrder%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderId%3D37174%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_backURL%3D%252Fgroup%252Ftest-1%252Fpending-orders",promoPrice:"",total:"$304.00",price:"$152.00",name:"Ball Joints",shippedQuantity:0,sku:"MIN38794"},{thumbnail:"https://next.clayui.com/images/long_user_image.png",quantity:1,actionItems:[{icon:"",href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=1&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_javax.portlet.action=editCommerceOrderItem&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_cmd=delete&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_redirect=%2Fgroup%2Ftest-1%2Fpending-orders%3Fp_p_id%3Dcom_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet%26p_p_lifecycle%3D0%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName%3DeditCommerceOrder%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderId%3D37174%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_backURL%3D%252Fgroup%252Ftest-1%252Fpending-orders&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderItemId=37177&p_auth=NrI5slwn",label:"Delete",quickAction:false,separator:false,order:0}],orderId:37174,orderItemId:37177,discount:"",viewShipmentsURL:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=pop_up&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName=viewCommerceOrderShipments&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderItemId=37174&p_r_p_backURL=%2Fgroup%2Ftest-1%2Fpending-orders%3Fp_p_id%3Dcom_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet%26p_p_lifecycle%3D0%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName%3DeditCommerceOrder%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderId%3D37174%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_backURL%3D%252Fgroup%252Ftest-1%252Fpending-orders",promoPrice:"",total:"$70.00",price:"$70.00",name:"Bearings",shippedQuantity:0,sku:"MIN00673"},{thumbnail:"https://next.clayui.com/images/long_user_image.png",quantity:2,actionItems:[{icon:"",href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=1&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_javax.portlet.action=editCommerceOrderItem&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_cmd=delete&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_redirect=%2Fgroup%2Ftest-1%2Fpending-orders%3Fp_p_id%3Dcom_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet%26p_p_lifecycle%3D0%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName%3DeditCommerceOrder%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderId%3D37174%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_backURL%3D%252Fgroup%252Ftest-1%252Fpending-orders&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderItemId=37178&p_auth=NrI5slwn",label:"Delete",quickAction:false,separator:false,order:0}],orderId:37174,orderItemId:37178,discount:"",viewShipmentsURL:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=pop_up&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName=viewCommerceOrderShipments&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderItemId=37174&p_r_p_backURL=%2Fgroup%2Ftest-1%2Fpending-orders%3Fp_p_id%3Dcom_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet%26p_p_lifecycle%3D0%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName%3DeditCommerceOrder%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderId%3D37174%26_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_backURL%3D%252Fgroup%252Ftest-1%252Fpending-orders",promoPrice:"$18.90",total:"$37.80",price:"$21.00",name:"Brake Pads",shippedQuantity:0,sku:"MIN93018"}],
	schema: {fields:[{fieldName:"sku",contentRenderer:"commerceTableCellImageName",label:"sku",sortable:false},{fieldName:"name",label:"name",sortable:false},{fieldName:"price",contentRenderer:"commerceTablePrice",label:"price",sortable:false},{fieldName:"discount",label:"discount",sortable:false},{fieldName:"quantity",label:"quantity",sortable:false},{fieldName:"total",label:"total",sortable:false}]},
	spritemap: './icons.svg',
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
	pageSize: 5,
	paginationEntries: [{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=5",label:5},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=10",label:10},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=20",label:20},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=30",label:30},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=50",label:50},{href:"http://localhost:8080/group/test-1/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_delta=75",label:75}],
	paginationSelectedEntry: 0,
	selectable: false,
	summaryName: 'Table summary',
	tableName: 'Orders',
	totalItems: 4,
});
