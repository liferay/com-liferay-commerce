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

package com.liferay.commerce.taglib.servlet.taglib;

import com.liferay.commerce.constants.CPDefinitionInventoryConstants;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionServiceUtil;
import com.liferay.commerce.service.CPDefinitionInventoryLocalServiceUtil;
import com.liferay.commerce.taglib.servlet.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Marco Leo
 */
public class QuantityInputTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		try {
			_allowedOrderQuantities = new int[0];
			_maxOrderQuantity =
				CPDefinitionInventoryConstants.DEFAULT_MAX_ORDER_QUANTITY;
			_minOrderQuantity =
				CPDefinitionInventoryConstants.DEFAULT_MIN_ORDER_QUANTITY;
			_multipleOrderQuantity =
				CPDefinitionInventoryConstants.DEFAULT_MULTIPLE_ORDER_QUANTITY;

			CPDefinitionInventory cpDefinitionInventory =
				CPDefinitionInventoryLocalServiceUtil.
					fetchCPDefinitionInventoryByCPDefinitionId(_cpDefinitionId);

			if (cpDefinitionInventory != null) {
				_allowedOrderQuantities =
					cpDefinitionInventory.getAllowedOrderQuantitiesArray();
				_maxOrderQuantity = cpDefinitionInventory.getMaxOrderQuantity();
				_minOrderQuantity = cpDefinitionInventory.getMinOrderQuantity();
				_multipleOrderQuantity =
					cpDefinitionInventory.getMultipleOrderQuantity();
			}

			_cpDefinition = CPDefinitionServiceUtil.getCPDefinition(
				_cpDefinitionId);

			if (_value == 0) {
				_value = _minOrderQuantity;
			}
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	public long getCPDefinitionId() {
		return _cpDefinitionId;
	}

	public String getName() {
		return _name;
	}

	public int getValue() {
		return _value;
	}

	public boolean isShowLabel() {
		return _showLabel;
	}

	public boolean isUseSelect() {
		return _useSelect;
	}

	public void setCPDefinitionId(long cpDefinitionId) {
		_cpDefinitionId = cpDefinitionId;
	}

	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setShowLabel(boolean showLabel) {
		_showLabel = showLabel;
	}

	public void setUseSelect(boolean useSelect) {
		_useSelect = useSelect;
	}

	public void setValue(int value) {
		_value = value;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_allowedOrderQuantities = null;
		_cpDefinition = null;
		_cpDefinitionId = 0;
		_maxOrderQuantity = 0;
		_minOrderQuantity = 0;
		_multipleOrderQuantity = 0;
		_name = null;
		_showLabel = true;
		_useSelect = true;
		_value = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		request.setAttribute(
			"liferay-commerce:quantity-input:allowedOrderQuantities",
			_allowedOrderQuantities);
		request.setAttribute(
			"liferay-commerce:quantity-input:cpDefinition", _cpDefinition);
		request.setAttribute(
			"liferay-commerce:quantity-input:maxOrderQuantity",
			_maxOrderQuantity);
		request.setAttribute(
			"liferay-commerce:quantity-input:minOrderQuantity",
			_minOrderQuantity);
		request.setAttribute(
			"liferay-commerce:quantity-input:multipleOrderQuantity",
			_multipleOrderQuantity);
		request.setAttribute("liferay-commerce:quantity-input:name", _name);
		request.setAttribute(
			"liferay-commerce:quantity-input:showLabel", _showLabel);
		request.setAttribute(
			"liferay-commerce:quantity-input:useSelect", _useSelect);
		request.setAttribute("liferay-commerce:quantity-input:value", _value);
	}

	private static final String _PAGE = "/quantity_input/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		QuantityInputTag.class);

	private int[] _allowedOrderQuantities;
	private CPDefinition _cpDefinition;
	private long _cpDefinitionId;
	private int _maxOrderQuantity;
	private int _minOrderQuantity;
	private int _multipleOrderQuantity;
	private String _name;
	private boolean _showLabel = true;
	private boolean _useSelect = true;
	private int _value;

}