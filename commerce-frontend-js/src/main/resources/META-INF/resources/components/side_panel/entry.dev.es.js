import launcher from './entry.es';

import '../../styles/main.scss';

window.SidePanel = launcher('sidePanel', 'side-panel', {
	containerSelector: '.smart-table-wrapper',
	id: 'sidePanelTestId',
	size: 'md',
	spritemap: './assets/icons.svg',
	topAnchorSelector: '.top-anchor',
	items: [
		{
			slug: 'comments',
			href: 'http://0.0.0.0:9000/group/minium',
			icon: 'comments'
		},
		{
			slug: 'edit',
			href: 'http://0.0.0.0:9000/group/minium/pending-orders?p_p_id=com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet&p_p_lifecycle=0&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_mvcRenderCommandName=editCommerceOrder&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_commerceOrderId=41475&_com_liferay_commerce_order_content_web_internal_portlet_CommerceOpenOrderContentPortlet_backURL=%2Fgroup%2Fminium%2Fpending-orders',
			icon: 'pencil'
		},
		{
			slug: 'changelog',
			href: 'http://0.0.0.0:9000/group/minium/account-management?p_p_id=com_liferay_commerce_account_web_internal_portlet_CommerceAccountPortlet&p_p_lifecycle=0&_com_liferay_commerce_account_web_internal_portlet_CommerceAccountPortlet_mvcRenderCommandName=viewCommerceAccount&_com_liferay_commerce_account_web_internal_portlet_CommerceAccountPortlet_commerceAccountId=40312&p_r_p_backURL=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fminium%2Faccount-management%3Fp_p_id%3Dcom_liferay_commerce_account_web_internal_portlet_CommerceAccountPortlet%26p_p_lifecycle%3D0',
			icon: 'restore'
		},
	]
});
