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
import com.liferay.commerce.product.permission.CommerceProductViewPermission;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProvider;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContext;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderException;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponseOutput;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

	@Override
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

		long cpDefinitionId = GetterUtil.getLong(
			ddmDataProviderRequest.getParameter("cpDefinitionId"));

		long commerceAccountId = GetterUtil.getLong(
			ddmDataProviderRequest.getParameter("commerceAccountId"));

		long groupId = GetterUtil.getLong(
			ddmDataProviderRequest.getParameter("groupId"));

		try {
			if (!_commerceProductViewPermission.contains(
					PermissionThreadLocal.getPermissionChecker(),
					commerceAccountId, groupId, cpDefinitionId)) {

				return ddmDataProviderResponseBuilder.build();
			}
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			return ddmDataProviderResponseBuilder.build();
		}

		if (cpDefinitionId == 0) {
			return ddmDataProviderResponseBuilder.build();
		}

		HttpServletRequest httpServletRequest =
			ddmDataProviderRequest.getHttpServletRequest();

		Locale locale = httpServletRequest.getLocale();

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
			Map<String, String> parameters =
				ddmDataProviderRequest.getParameters();

			Map<String, String> outputParameterNames = new HashMap<>();

			Map<String, String> filters = new HashMap<>();

			List<CPDefinitionOptionRel> cpDefinitionOptionRels =
				_cpDefinitionOptionRelLocalService.getCPDefinitionOptionRels(
					cpDefinitionId, true);

			for (CPDefinitionOptionRel cpDefinitionOptionRel :
					cpDefinitionOptionRels) {

				String parameterValue = parameters.get(
					cpDefinitionOptionRel.getKey());

				// Collect filters and outputs

				if (Validator.isNull(parameterValue)) {
					outputParameterNames.put(
						cpDefinitionOptionRel.getKey(),
						cpDefinitionOptionRel.getKey());
				}
				else {
					filters.put(cpDefinitionOptionRel.getKey(), parameterValue);
				}
			}

			// Do search and populate the outputs if the outputs are not empty

			if (outputParameterNames.isEmpty()) {
				return ddmDataProviderResponseBuilder.build();
			}

			List<Output> outputs = new ArrayList<>();

			for (Map.Entry<String, String> outputParameterNameEntry :
					outputParameterNames.entrySet()) {

				List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
					_cpInstanceHelper.getCPDefinitionOptionValueRel(
						cpDefinitionId, outputParameterNameEntry.getKey(),
						filters);

				List<KeyValuePair> data = new ArrayList<>();

				for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
						cpDefinitionOptionValueRels) {

					data.add(
						new KeyValuePair(
							cpDefinitionOptionValueRel.getKey(),
							cpDefinitionOptionValueRel.getName(locale)));
				}

				outputs.add(
					new Output(
						outputParameterNameEntry.getValue(), "list", data));
			}

			return Output.toDDMDataProviderResponse(outputs);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return ddmDataProviderResponseBuilder.build();
	}

	@Override
	public Class<?> getSettings() {
		throw new UnsupportedOperationException();
	}

	protected static class Output {

		public Output(String name, String type, Object value) {
			_name = name;
			_type = type;
			_value = value;
		}

		public static DDMDataProviderResponse toDDMDataProviderResponse(
			List<Output> outputs) {

			if (ReleaseInfo.getBuildNumber() >= _RELEASE_7_2_0_BUILD_NUMBER) {
				DDMDataProviderResponse.Builder ddmDataProviderResponseBuilder =
					DDMDataProviderResponse.Builder.newBuilder();

				for (Output output : outputs) {
					ddmDataProviderResponseBuilder.withOutput(
						output._name, output._value);
				}

				return ddmDataProviderResponseBuilder.build();
			}

			DDMDataProviderResponseOutput[] ddmDataProviderResponseOutputs =
				new DDMDataProviderResponseOutput[outputs.size()];

			for (int i = 0; i < outputs.size(); i++) {
				Output output = outputs.get(i);

				ddmDataProviderResponseOutputs[i] =
					DDMDataProviderResponseOutput.of(
						output._name, output._type, output._value);
			}

			return DDMDataProviderResponse.of(ddmDataProviderResponseOutputs);
		}

		private final String _name;
		private final String _type;
		private final Object _value;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPInstanceOptionsValuesDataProvider.class);

	private static final int _RELEASE_7_2_0_BUILD_NUMBER =
		ReleaseInfo.RELEASE_7_1_0_BUILD_NUMBER + 100;

	@Reference
	private CommerceProductViewPermission _commerceProductViewPermission;

	@Reference
	private CPDefinitionOptionRelLocalService
		_cpDefinitionOptionRelLocalService;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

}