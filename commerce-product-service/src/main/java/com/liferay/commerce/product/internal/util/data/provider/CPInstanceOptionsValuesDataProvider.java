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

package com.liferay.commerce.product.internal.util.data.provider;

import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.permission.CommerceProductViewPermission;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProvider;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContext;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderException;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponseStatus;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = "ddm.data.provider.instance.id=getCPInstanceOptionsValues",
	service = DDMDataProvider.class
)
public class CPInstanceOptionsValuesDataProvider implements DDMDataProvider {

	public List<KeyValuePair> getData(
			DDMDataProviderContext ddmDataProviderContext)
		throws DDMDataProviderException {

		return Collections.emptyList();
	}

	@Override
	public DDMDataProviderResponse getData(
			DDMDataProviderRequest ddmDataProviderRequest)
		throws DDMDataProviderException {

		DDMDataProviderResponse.Builder ddmDataProviderResponseBuilder =
			DDMDataProviderResponse.Builder.newBuilder();

		Optional<Object> cpDefinitionIdOptional =
			ddmDataProviderRequest.getParameterOptional(
				"cpDefinitionId", Object.class);

		long cpDefinitionId = GetterUtil.getLong(
			cpDefinitionIdOptional.orElse(0));

		Optional<Object> commerceAccountIdOptional =
			ddmDataProviderRequest.getParameterOptional(
				"commerceAccountId", Object.class);

		long commerceAccountId = GetterUtil.getLong(
			commerceAccountIdOptional.orElse(0));

		Optional<Object> groupIdOptional =
			ddmDataProviderRequest.getParameterOptional(
				"groupId", Object.class);

		long groupId = GetterUtil.getLong(groupIdOptional.orElse(0));

		try {
			if (!_commerceProductViewPermission.contains(
					PermissionThreadLocal.getPermissionChecker(),
					commerceAccountId, groupId, cpDefinitionId)) {

				return ddmDataProviderResponseBuilder.withStatus(
					DDMDataProviderResponseStatus.UNAUTHORIZED
				).build();
			}
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			return ddmDataProviderResponseBuilder.withStatus(
				DDMDataProviderResponseStatus.UNAUTHORIZED
			).build();
		}

		if (cpDefinitionId == 0) {
			return ddmDataProviderResponseBuilder.build();
		}

		Locale locale = ddmDataProviderRequest.getLocale();

		try {

			/**
			 * Extract the filters and the outputs based on fields that were
			 * filled or not in the data provider request
			 * Ex:
			 * If we have size and color as product options,
			 * In this case, we will have four possible states as follow:
			 * 1 - Size and Color both empty -> It won't do any query and it
			 * 	will return an empty result.
			 * 2 - Size filled and Color empty -> It'll assume that size will
			 * 	compose the query filter and color will be the output. So,
			 * 	it will return all colors for the specified size
			 * 3 - Size empty and Color filled - the same approach that item 2
			 * 4 - Size and Color both filled - the same approach that item 1
			 */
			Map<String, String> outputParameterNames = new HashMap<>();

			Map<String, String> filters = new HashMap<>();

			List<CPDefinitionOptionRel> cpDefinitionOptionRels =
				_cpDefinitionOptionRelLocalService.getCPDefinitionOptionRels(
					cpDefinitionId, true);

			for (CPDefinitionOptionRel cpDefinitionOptionRel :
					cpDefinitionOptionRels) {

				CPOption cpOption = cpDefinitionOptionRel.getCPOption();

				Optional<Object> parameterValueOptional =
					ddmDataProviderRequest.getParameterOptional(
						cpOption.getKey(), Object.class);

				String parameterValue = String.valueOf(
					parameterValueOptional.orElse(StringPool.BLANK));

				// Collect filters and outputs

				if (Validator.isNull(parameterValue)) {
					outputParameterNames.put(
						cpOption.getKey(), cpOption.getKey());
				}
				else {
					filters.put(cpOption.getKey(), parameterValue);
				}
			}

			// Do search and populate the outputs if the outputs are not empty

			if (outputParameterNames.isEmpty()) {
				return ddmDataProviderResponseBuilder.build();
			}

			for (Map.Entry<String, String> outputParameterNameEntry :
					outputParameterNames.entrySet()) {

				String fieldName =
					"ATTRIBUTE_" + outputParameterNameEntry.getKey() +
						"_VALUE_ID";

				List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
					_cpInstanceHelper.getCPDefinitionOptionValueRel(
						cpDefinitionId, fieldName, filters);

				List<KeyValuePair> data = new ArrayList<>();

				for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
						cpDefinitionOptionValueRels) {

					data.add(
						new KeyValuePair(
							cpDefinitionOptionValueRel.getKey(),
							cpDefinitionOptionValueRel.getName(locale)));
				}

				ddmDataProviderResponseBuilder.withOutput(
					outputParameterNameEntry.getValue(), data);
			}

			return ddmDataProviderResponseBuilder.build();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		ddmDataProviderResponseBuilder =
			DDMDataProviderResponse.Builder.newBuilder();

		return ddmDataProviderResponseBuilder.build();
	}

	@Override
	public Class<?> getSettings() {
		throw new UnsupportedOperationException();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPInstanceOptionsValuesDataProvider.class);

	@Reference
	private CommerceProductViewPermission _commerceProductViewPermission;

	@Reference
	private CPDefinitionOptionRelLocalService
		_cpDefinitionOptionRelLocalService;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

}