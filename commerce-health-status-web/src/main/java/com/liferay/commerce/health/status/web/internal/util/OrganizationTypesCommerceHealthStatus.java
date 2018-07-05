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

package com.liferay.commerce.health.status.web.internal.util;

import com.liferay.commerce.health.status.CommerceHealthStatus;
import com.liferay.commerce.health.status.web.internal.constants.CommerceHealthStatusConstants;
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.service.CPMeasurementUnitLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.Dictionary;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.health.status.display.order:Integer=110",
		"commerce.health.status.key=" + CommerceHealthStatusConstants.ORGANIZATION_TYPES_COMMERCE_HEALTH_STATUS_KEY
	},
	service = CommerceHealthStatus.class
)
public class OrganizationTypesCommerceHealthStatus
	implements CommerceHealthStatus {

	@Override
	public void fixIssue(HttpServletRequest httpServletRequest)
		throws PortalException {

		try {
			Configuration[] configurations =
				_configurationAdmin.listConfigurations(
					_getConfigurationFilter());

			for (Configuration configuration : configurations) {
				Dictionary<String, Object> properties =
					configuration.getProperties();

				String name = (String) properties.get("name");

				if (name.equals(OrganizationConstants.TYPE_ORGANIZATION)) {
					properties.put("childrenTypes", _CHILDREN_TYPES);

					configuration.update(properties);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle,
			CommerceHealthStatusConstants.
				MEASUREMENT_UNITS_COMMERCE_HEALTH_STATUS_DESCRIPTION);
	}

	@Override
	public String getKey() {
		return CommerceHealthStatusConstants.
			ORGANIZATION_TYPES_COMMERCE_HEALTH_STATUS_KEY;
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle,
			CommerceHealthStatusConstants.
				ORGANIZATION_TYPES_COMMERCE_HEALTH_STATUS_KEY);
	}

	@Override
	public boolean isFixed(long groupId) throws PortalException {
		String[] childrenTypes = _organizationLocalService.getChildrenTypes(
			OrganizationConstants.TYPE_ORGANIZATION);

		return ArrayUtil.containsAll(childrenTypes, _CHILDREN_TYPES);
	}

	private String _getConfigurationFilter() {
		StringBundler sb = new StringBundler(5);

		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(ConfigurationAdmin.SERVICE_FACTORYPID);
		sb.append(StringPool.EQUAL);
		sb.append(_ORGANIZATION_TYPE_CONFIGURATION_PID);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	private static final String[] _CHILDREN_TYPES =
		new String[] {"account", "organization"};

	private static final Log _log = LogFactoryUtil.getLog(
		OrganizationTypesCommerceHealthStatus.class);

	private static final String _ORGANIZATION_TYPE_CONFIGURATION_PID =
		"com.liferay.organizations.service.internal.configuration." +
			"OrganizationTypeConfiguration";

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private OrganizationLocalService _organizationLocalService;

}