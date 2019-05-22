/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.order.content.web.internal.portlet;

import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutFriendlyURLComposite;
import com.liferay.portal.kernel.portlet.FriendlyURLResolver;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.InheritableMap;
import com.liferay.portal.kernel.util.Portal;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = FriendlyURLResolver.class)
public class CommerceOrderFriendlyURLResolver implements FriendlyURLResolver {

	@Override
	public String getActualURL(
			long companyId, long groupId, boolean privateLayout,
			String mainPath, String friendlyURL, Map<String, String[]> params,
			Map<String, Object> requestContext)
		throws PortalException {

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)requestContext.get("request");

		String urlTitle = friendlyURL.substring(
			CommerceOrderConstants.SEPARATOR_ORDER_DETAIL_URL.length());

		long commerceOrderId = GetterUtil.getLong(urlTitle);

		CommerceOrder commerceOrder = _commerceOrderService.fetchCommerceOrder(
			commerceOrderId);

		if (commerceOrder == null) {
			return null;
		}

		Layout layout = getOrderDetailLayout(groupId, privateLayout);

		String layoutActualURL = _portal.getLayoutActualURL(layout, mainPath);

		InheritableMap<String, String[]> actualParams = new InheritableMap<>();

		if (params != null) {
			actualParams.setParentMap(params);
		}

		actualParams.put("p_p_lifecycle", new String[] {"0"});
		actualParams.put("p_p_mode", new String[] {"view"});

		httpServletRequest.setAttribute(
			CommerceWebKeys.COMMERCE_ORDER, commerceOrder);

		_commerceAccountHelper.setCurrentCommerceAccount(
			httpServletRequest, groupId, commerceOrder.getCommerceAccountId());

		String queryString = _http.parameterMapToString(actualParams, false);

		if (layoutActualURL.contains(StringPool.QUESTION)) {
			layoutActualURL =
				layoutActualURL + StringPool.AMPERSAND + queryString;
		}
		else {
			layoutActualURL =
				layoutActualURL + StringPool.QUESTION + queryString;
		}

		return layoutActualURL;
	}

	@Override
	public LayoutFriendlyURLComposite getLayoutFriendlyURLComposite(
			long companyId, long groupId, boolean privateLayout,
			String friendlyURL, Map<String, String[]> params,
			Map<String, Object> requestContext)
		throws PortalException {

		String urlTitle = friendlyURL.substring(
			CommerceOrderConstants.SEPARATOR_ORDER_DETAIL_URL.length());

		long commerceOrderId = GetterUtil.getLong(urlTitle);

		CommerceOrder commerceOrder = _commerceOrderService.fetchCommerceOrder(
			commerceOrderId);

		if (commerceOrder == null) {
			return null;
		}

		Layout layout = getOrderDetailLayout(groupId, privateLayout);

		return new LayoutFriendlyURLComposite(
			layout, getURLSeparator() + commerceOrder.getCommerceOrderId());
	}

	@Override
	public String getURLSeparator() {
		return CommerceOrderConstants.SEPARATOR_ORDER_DETAIL_URL;
	}

	protected Layout getOrderDetailLayout(long groupId, boolean privateLayout)
		throws PortalException {

		long plid = _portal.getPlidFromPortletId(
			groupId, privateLayout, CommercePortletKeys.COMMERCE_ORDER_DETAIL);

		return _layoutLocalService.getLayout(plid);
	}

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private Http _http;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}