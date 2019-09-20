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

package com.liferay.commerce.taglib.servlet.taglib.internal.servlet;

import com.liferay.application.list.PanelAppRegistry;
import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHelper;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.product.util.CPSubscriptionTypeRegistry;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 * @author Luca Pellizzon
 */
@Component(immediate = true, service = ServletContextUtil.class)
public class ServletContextUtil {

	public static final CommerceOrderHelper getCommerceOrderHelper() {
		return _instance._getCommerceOrderHelper();
	}

	public static final ModelResourcePermission<CommerceOrder>
		getCommerceOrderModelResourcePermission() {

		return _instance._getCommerceOrderModelResourcePermission();
	}

	public static final CommerceProductPriceCalculation
		getCommercePriceCalculation() {

		return _instance._getCommercePriceCalculation();
	}

	public static final CommercePriceFormatter getCommercePriceFormatter() {
		return _instance._getCommercePriceFormatter();
	}

	public static final CommercePriceListLocalService
		getCommercePriceListLocalService() {

		return _instance._getCommercePriceListLocalService();
	}

	public static final ConfigurationProvider getConfigurationProvider() {
		return _instance._getConfigurationProvider();
	}

	public static final CPDefinitionHelper getCPDefinitionHelper() {
		return _instance._getCPDefinitionHelper();
	}

	public static final CPInstanceHelper getCPInstanceHelper() {
		return _instance._getCPInstanceHelper();
	}

	public static final CPSubscriptionTypeRegistry
		getCPSubscriptionTypeRegistry() {

		return _instance._getCPSubscriptionTypeRegistry();
	}

	public static final PanelAppRegistry getPanelAppRegistry() {
		return _instance._getPanelAppRegistry();
	}

	public static final PanelCategoryRegistry getPanelCategoryRegistry() {
		return _instance._getPanelCategoryRegistry();
	}

	public static final ServletContext getServletContext() {
		return _instance._getServletContext();
	}

	@Activate
	protected void activate() {
		_instance = this;
	}

	@Deactivate
	protected void deactivate() {
		_instance = null;
	}

	@Reference(unbind = "-")
	protected void setCommerceOrderHelper(
		CommerceOrderHelper commerceOrderHelper) {

		_commerceOrderHelper = commerceOrderHelper;
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrder)",
		unbind = "-"
	)
	protected void setCommerceOrderModelResourcePermission(
		ModelResourcePermission<CommerceOrder>
			commerceOrderModelResourcePermission) {

		_commerceOrderModelResourcePermission =
			commerceOrderModelResourcePermission;
	}

	@Reference(unbind = "-")
	protected void setCommercePriceCalculation(
		CommerceProductPriceCalculation commerceProductPriceCalculation) {

		_commerceProductPriceCalculation = commerceProductPriceCalculation;
	}

	@Reference(unbind = "-")
	protected void setCommercePriceFormatter(
		CommercePriceFormatter commercePriceFormatter) {

		_commercePriceFormatter = commercePriceFormatter;
	}

	@Reference(unbind = "-")
	protected void setCommercePriceListLocalService(
		CommercePriceListLocalService commercePriceListLocalService) {

		_commercePriceListLocalService = commercePriceListLocalService;
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	@Reference(unbind = "-")
	protected void setCPDefinitionHelper(
		CPDefinitionHelper cpDefinitionHelper) {

		_cpDefinitionHelper = cpDefinitionHelper;
	}

	@Reference(unbind = "-")
	protected void setCPInstanceHelper(CPInstanceHelper cpInstanceHelper) {
		_cpInstanceHelper = cpInstanceHelper;
	}

	@Reference(unbind = "-")
	protected void setCPSubscriptionTypeRegistry(
		CPSubscriptionTypeRegistry cpSubscriptionTypeRegistry) {

		_cpSubscriptionTypeRegistry = cpSubscriptionTypeRegistry;
	}

	@Reference(unbind = "-")
	protected void setPanelAppRegistry(PanelAppRegistry panelAppRegistry) {
		_panelAppRegistry = panelAppRegistry;
	}

	@Reference(unbind = "-")
	protected void setPanelCategoryRegistry(
		PanelCategoryRegistry panelCategoryRegistry) {

		_panelCategoryRegistry = panelCategoryRegistry;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.taglib)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private CommerceOrderHelper _getCommerceOrderHelper() {
		return _commerceOrderHelper;
	}

	private ModelResourcePermission<CommerceOrder>
		_getCommerceOrderModelResourcePermission() {

		return _commerceOrderModelResourcePermission;
	}

	private CommerceProductPriceCalculation _getCommercePriceCalculation() {
		return _commerceProductPriceCalculation;
	}

	private CommercePriceFormatter _getCommercePriceFormatter() {
		return _commercePriceFormatter;
	}

	private CommercePriceListLocalService _getCommercePriceListLocalService() {
		return _commercePriceListLocalService;
	}

	private ConfigurationProvider _getConfigurationProvider() {
		return _configurationProvider;
	}

	private CPDefinitionHelper _getCPDefinitionHelper() {
		return _cpDefinitionHelper;
	}

	private CPInstanceHelper _getCPInstanceHelper() {
		return _cpInstanceHelper;
	}

	private CPSubscriptionTypeRegistry _getCPSubscriptionTypeRegistry() {
		return _cpSubscriptionTypeRegistry;
	}

	private PanelAppRegistry _getPanelAppRegistry() {
		return _panelAppRegistry;
	}

	private PanelCategoryRegistry _getPanelCategoryRegistry() {
		return _panelCategoryRegistry;
	}

	private ServletContext _getServletContext() {
		return _servletContext;
	}

	private static ServletContextUtil _instance;

	private CommerceOrderHelper _commerceOrderHelper;
	private ModelResourcePermission<CommerceOrder>
		_commerceOrderModelResourcePermission;
	private CommercePriceFormatter _commercePriceFormatter;
	private CommercePriceListLocalService _commercePriceListLocalService;
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;
	private ConfigurationProvider _configurationProvider;
	private CPDefinitionHelper _cpDefinitionHelper;
	private CPInstanceHelper _cpInstanceHelper;
	private CPSubscriptionTypeRegistry _cpSubscriptionTypeRegistry;
	private PanelAppRegistry _panelAppRegistry;
	private PanelCategoryRegistry _panelCategoryRegistry;
	private ServletContext _servletContext;

}