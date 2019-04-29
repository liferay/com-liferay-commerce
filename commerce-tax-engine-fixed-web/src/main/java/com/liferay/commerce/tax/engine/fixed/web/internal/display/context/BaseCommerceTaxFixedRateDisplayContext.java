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

package com.liferay.commerce.tax.engine.fixed.web.internal.display.context;

import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.constants.CommerceTaxScreenNavigationConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.tax.engine.fixed.web.internal.display.context.util.CommerceTaxFixedRateRequestHelper;
import com.liferay.commerce.tax.model.CommerceTaxMethod;
import com.liferay.commerce.tax.service.CommerceTaxMethodService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public abstract class BaseCommerceTaxFixedRateDisplayContext<T> {

	public BaseCommerceTaxFixedRateDisplayContext(
		CommerceCurrencyLocalService commerceCurrencyLocalService,
		CommerceTaxMethodService commerceTaxMethodService,
		RenderRequest renderRequest) {

		this.commerceCurrencyLocalService = commerceCurrencyLocalService;
		this.commerceTaxMethodService = commerceTaxMethodService;

		commerceTaxFixedRateRequestHelper =
			new CommerceTaxFixedRateRequestHelper(renderRequest);

		_defaultOrderByCol = "create-date";
		_defaultOrderByType = "desc";
	}

	public String getCommerceCurrencyCode() throws PortalException {
		CommerceTaxMethod commerceTaxMethod = getCommerceTaxMethod();

		if (commerceTaxMethod.isPercentage()) {
			return StringPool.PERCENT;
		}

		CommerceCurrency commerceCurrency =
			commerceCurrencyLocalService.fetchPrimaryCommerceCurrency(
				commerceTaxFixedRateRequestHelper.getScopeGroupId());

		if (commerceCurrency != null) {
			return commerceCurrency.getCode();
		}

		return StringPool.BLANK;
	}

	public CommerceTaxMethod getCommerceTaxMethod() throws PortalException {
		if (_commerceTaxMethod != null) {
			return _commerceTaxMethod;
		}

		long commerceTaxMethodId = getCommerceTaxMethodId();

		if (commerceTaxMethodId > 0) {
			_commerceTaxMethod = commerceTaxMethodService.getCommerceTaxMethod(
				commerceTaxMethodId);
		}

		return _commerceTaxMethod;
	}

	public long getCommerceTaxMethodId() throws PortalException {
		return ParamUtil.getLong(
			commerceTaxFixedRateRequestHelper.getRequest(),
			"commerceTaxMethodId");
	}

	public String getOrderByCol() {
		return ParamUtil.getString(
			commerceTaxFixedRateRequestHelper.getRequest(),
			SearchContainer.DEFAULT_ORDER_BY_COL_PARAM, _defaultOrderByCol);
	}

	public String getOrderByType() {
		return ParamUtil.getString(
			commerceTaxFixedRateRequestHelper.getRequest(),
			SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM, _defaultOrderByType);
	}

	public PortletURL getPortletURL() throws PortalException {
		LiferayPortletResponse liferayPortletResponse =
			commerceTaxFixedRateRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"commerceAdminModuleKey",
			CommerceConstants.TAXES_COMMERCE_ADMIN_MODULE_KEY);
		portletURL.setParameter(
			"mvcRenderCommandName", "editCommerceTaxMethod");
		portletURL.setParameter(
			"screenNavigationCategoryKey",
			getSelectedScreenNavigationCategoryKey());

		CommerceTaxMethod commerceTaxMethod = getCommerceTaxMethod();

		if (commerceTaxMethod != null) {
			portletURL.setParameter(
				"commerceTaxMethodId",
				String.valueOf(commerceTaxMethod.getCommerceTaxMethodId()));
		}

		String engineKey = ParamUtil.getString(
			commerceTaxFixedRateRequestHelper.getRequest(), "engineKey");

		if (Validator.isNotNull(engineKey)) {
			portletURL.setParameter("engineKey", engineKey);
		}

		String delta = ParamUtil.getString(
			commerceTaxFixedRateRequestHelper.getRequest(), "delta");

		if (Validator.isNotNull(delta)) {
			portletURL.setParameter("delta", delta);
		}

		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public RowChecker getRowChecker() {
		if (_rowChecker == null) {
			_rowChecker = new EmptyOnClickRowChecker(
				commerceTaxFixedRateRequestHelper.getLiferayPortletResponse());
		}

		return _rowChecker;
	}

	public String getScreenNavigationCategoryKey() {
		return CommerceTaxScreenNavigationConstants.
			CATEGORY_KEY_COMMERCE_TAX_METHOD_DETAIL;
	}

	public abstract SearchContainer<T> getSearchContainer()
		throws PortalException;

	public String getTaxCategoriesURL() {
		LiferayPortletResponse liferayPortletResponse =
			commerceTaxFixedRateRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"commerceAdminModuleKey",
			CommerceConstants.TAXES_COMMERCE_ADMIN_MODULE_KEY);
		portletURL.setParameter(
			"screenNavigationCategoryKey", "tax-categories");

		return portletURL.toString();
	}

	public void setDefaultOrderByCol(String defaultOrderByCol) {
		_defaultOrderByCol = defaultOrderByCol;
	}

	public void setDefaultOrderByType(String defaultOrderByType) {
		_defaultOrderByType = defaultOrderByType;
	}

	protected String getSelectedScreenNavigationCategoryKey() {
		return ParamUtil.getString(
			commerceTaxFixedRateRequestHelper.getRequest(),
			"screenNavigationCategoryKey", getScreenNavigationCategoryKey());
	}

	protected final CommerceCurrencyLocalService commerceCurrencyLocalService;
	protected final CommerceTaxFixedRateRequestHelper
		commerceTaxFixedRateRequestHelper;
	protected final CommerceTaxMethodService commerceTaxMethodService;
	protected SearchContainer<T> searchContainer;

	private CommerceTaxMethod _commerceTaxMethod;
	private String _defaultOrderByCol;
	private String _defaultOrderByType;
	private RowChecker _rowChecker;

}