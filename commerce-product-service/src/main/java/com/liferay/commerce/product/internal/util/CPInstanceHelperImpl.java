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

package com.liferay.commerce.product.internal.util;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.media.CommerceMediaResolver;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.constants.CPField;
import com.liferay.commerce.product.internal.catalog.CPSkuImpl;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.product.util.DDMFormValuesHelper;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeServicesTracker;
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderer;
import com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderingContext;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormRule;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CPInstanceHelper.class)
public class CPInstanceHelperImpl implements CPInstanceHelper {

	@Override
	public List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long cpDefinitionId, String serializedDDMFormValues, int type)
		throws Exception {

		return getCPAttachmentFileEntries(
			cpDefinitionId, serializedDDMFormValues, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	@Override
	public List<CPAttachmentFileEntry> getCPAttachmentFileEntries(
			long cpDefinitionId, String serializedDDMFormValues, int type,
			int start, int end)
		throws Exception {

		List<CPAttachmentFileEntry> cpAttachmentFileEntries = new ArrayList<>();

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		long cpDefinitionClassNameId = _portal.getClassNameId(
			CPDefinition.class);

		JSONArray jsonArray = _jsonFactory.createJSONArray(
			serializedDDMFormValues);

		Indexer<CPAttachmentFileEntry> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(CPAttachmentFileEntry.class);

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(
			CPField.RELATED_ENTITY_CLASS_NAME_ID, cpDefinitionClassNameId);
		attributes.put(CPField.RELATED_ENTITY_CLASS_PK, cpDefinitionId);
		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
		attributes.put(Field.TYPE, type);

		List<String> optionsKeys = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			JSONArray valuesJSONArray = _jsonFactory.createJSONArray(
				jsonObject.getString("value"));

			String[] values = new String[valuesJSONArray.length()];

			if (values.length == 0) {
				continue;
			}

			for (int j = 0; j < valuesJSONArray.length(); j++) {
				values[j] = valuesJSONArray.getString(j);
			}

			String key = jsonObject.getString("key");

			String fieldName = "ATTRIBUTE_" + key + "_VALUES_IDS";

			attributes.put(fieldName, values);

			optionsKeys.add(fieldName);
		}

		attributes.put("OPTIONS", ArrayUtil.toStringArray(optionsKeys));

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(cpDefinition.getCompanyId());
		searchContext.setGroupIds(new long[] {cpDefinition.getGroupId()});
		searchContext.setStart(start);
		searchContext.setEnd(end);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		Sort prioritySort = SortFactoryUtil.create(Field.PRIORITY, false);

		searchContext.setSorts(prioritySort);

		queryConfig.addSelectedFieldNames(Field.ENTRY_CLASS_PK);

		Hits hits = indexer.search(searchContext);

		Document[] documents = hits.getDocs();

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			cpAttachmentFileEntries.add(
				_cpAttachmentFileEntryLocalService.getCPAttachmentFileEntry(
					classPK));
		}

		return cpAttachmentFileEntries;
	}

	@Override
	public DDMForm getCPAttachmentFileEntryDDMForm(
			long cpDefinitionId, Locale locale)
		throws PortalException {

		return _getDDMForm(cpDefinitionId, locale, false, true, true, false);
	}

	@Override
	public Map<CPDefinitionOptionRel, List<CPDefinitionOptionValueRel>>
			getCPDefinitionOptionRelsMap(long cpDefinitionId, String json)
		throws PortalException {

		Map<CPDefinitionOptionRel, List<CPDefinitionOptionValueRel>>
			cpDefinitionOptionRelListMap = new HashMap<>();

		if (Validator.isNull(json)) {
			return cpDefinitionOptionRelListMap;
		}

		JSONArray jsonArray = _jsonFactory.createJSONArray(json);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CPDefinitionOptionRel cpDefinitionOptionRel =
				_cpDefinitionOptionRelLocalService.
					fetchCPDefinitionOptionRelByKey(
						cpDefinitionId, jsonObject.getString("key"));

			if (cpDefinitionOptionRel == null) {
				continue;
			}

			JSONArray valueJSONArray = jsonObject.getJSONArray("value");

			for (int j = 0; j < valueJSONArray.length(); j++) {
				CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
					_cpDefinitionOptionValueRelLocalService.
						fetchCPDefinitionOptionValueRel(
							cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
							valueJSONArray.getString(j));

				if (cpDefinitionOptionValueRel == null) {
					continue;
				}

				List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
					cpDefinitionOptionRelListMap.get(cpDefinitionOptionRel);

				if (cpDefinitionOptionValueRels == null) {
					cpDefinitionOptionValueRels = new ArrayList<>();

					cpDefinitionOptionRelListMap.put(
						cpDefinitionOptionRel, cpDefinitionOptionValueRels);
				}

				cpDefinitionOptionValueRels.add(cpDefinitionOptionValueRel);
			}
		}

		return cpDefinitionOptionRelListMap;
	}

	@Override
	public List<CPDefinitionOptionValueRel> getCPDefinitionOptionValueRel(
			long cpDefinitionId, String optionKey,
			Map<String, String> optionMap)
		throws Exception {

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			new ArrayList<>();

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		CPDefinitionOptionRel cpDefinitionOptionRel =
			_cpDefinitionOptionRelLocalService.fetchCPDefinitionOptionRelByKey(
				cpDefinitionId, optionKey);

		Indexer<CPInstance> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPInstance.class);

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(CPField.CP_DEFINITION_ID, cpDefinitionId);
		attributes.put(CPField.PUBLISHED, Boolean.TRUE);
		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		List<String> optionsKeys = new ArrayList<>();

		for (Map.Entry<String, String> optionEntry : optionMap.entrySet()) {
			String fieldName =
				"ATTRIBUTE_" + optionEntry.getKey() + "_VALUE_ID";

			optionsKeys.add(fieldName);

			attributes.put(fieldName, optionEntry.getValue());
		}

		attributes.put("OPTIONS", ArrayUtil.toStringArray(optionsKeys));

		searchContext.setAttributes(attributes);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		searchContext.setCompanyId(cpDefinition.getCompanyId());
		searchContext.setGroupIds(new long[] {cpDefinition.getGroupId()});

		String optionFieldName = "ATTRIBUTE_" + optionKey + "_VALUE_ID";

		Hits hits = indexer.search(searchContext, optionFieldName);

		Document[] documents = hits.getDocs();

		for (Document document : documents) {
			String key = GetterUtil.getString(document.get(optionFieldName));

			cpDefinitionOptionValueRels.add(
				_cpDefinitionOptionValueRelLocalService.
					fetchCPDefinitionOptionValueRel(
						cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
						key));
		}

		return cpDefinitionOptionValueRels;
	}

	@Override
	public CPInstance getCPInstance(
			long cpDefinitionId, String serializedDDMFormValues)
		throws Exception {

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		if (Validator.isNull(serializedDDMFormValues)) {
			serializedDDMFormValues = "[]";
		}

		JSONArray jsonArray = _jsonFactory.createJSONArray(
			serializedDDMFormValues);

		Indexer<CPInstance> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			CPInstance.class);

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(CPField.CP_DEFINITION_ID, cpDefinitionId);
		attributes.put(CPField.PUBLISHED, Boolean.TRUE);
		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		List<String> optionsKeys = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String key = jsonObject.getString("key");

			CPDefinitionOptionRel cpDefinitionOptionRel =
				_cpDefinitionOptionRelLocalService.
					fetchCPDefinitionOptionRelByKey(cpDefinitionId, key);

			if ((cpDefinitionOptionRel != null) &&
				!cpDefinitionOptionRel.isSkuContributor()) {

				continue;
			}

			JSONArray valuesJSONArray = _jsonFactory.createJSONArray(
				jsonObject.getString("value"));

			if (valuesJSONArray.length() == 0) {
				continue;
			}

			String fieldName = "ATTRIBUTE_" + key + "_VALUE_ID";

			String value = valuesJSONArray.getString(0);

			optionsKeys.add(fieldName);
			attributes.put(fieldName, value);
		}

		attributes.put("OPTIONS", ArrayUtil.toStringArray(optionsKeys));

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(cpDefinition.getCompanyId());

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		Hits hits = indexer.search(searchContext);

		Document[] documents = hits.getDocs();

		if (documents.length != 1) {
			return null;
		}

		Document document = documents[0];

		long cpInstanceId = GetterUtil.getLong(
			document.get(Field.ENTRY_CLASS_PK));

		return _cpInstanceLocalService.fetchCPInstance(cpInstanceId);
	}

	@Override
	public DDMForm getCPInstanceDDMForm(
			long cpDefinitionId, Locale locale, boolean ignoreSKUCombinations,
			boolean skuContributor)
		throws PortalException {

		return _getDDMForm(
			cpDefinitionId, locale, ignoreSKUCombinations, skuContributor,
			false, false);
	}

	@Override
	public String getCPInstanceThumbnailSrc(long cpInstanceId)
		throws Exception {

		CPInstance cpInstance = _cpInstanceLocalService.fetchCPInstance(
			cpInstanceId);

		if (cpInstance == null) {
			return StringPool.BLANK;
		}

		List<CPAttachmentFileEntry> cpAttachmentFileEntries =
			getCPAttachmentFileEntries(
				cpInstance.getCPDefinitionId(), cpInstance.getJson(),
				CPAttachmentFileEntryConstants.TYPE_IMAGE, 0, 1);

		if (cpAttachmentFileEntries.isEmpty()) {
			CPDefinition cpDefinition = cpInstance.getCPDefinition();

			return cpDefinition.getDefaultImageThumbnailSrc();
		}

		CPAttachmentFileEntry cpAttachmentFileEntry =
			cpAttachmentFileEntries.get(0);

		return _commerceMediaResolver.getThumbnailUrl(
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());
	}

	@Override
	public CPSku getDefaultCPSku(CPCatalogEntry cpCatalogEntry)
		throws Exception {

		if (!cpCatalogEntry.isIgnoreSKUCombinations()) {
			return null;
		}

		CPInstance cpInstance = getCPInstance(
			cpCatalogEntry.getCPDefinitionId(), null);

		if (cpInstance == null) {
			return null;
		}

		return new CPSkuImpl(cpInstance);
	}

	@Override
	public List<KeyValuePair> getKeyValuePairs(
			long cpDefinitionId, String json, Locale locale)
		throws PortalException {

		List<KeyValuePair> values = new ArrayList<>();

		if (Validator.isNull(json)) {
			return values;
		}

		JSONArray jsonArray = _jsonFactory.createJSONArray(json);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String key = jsonObject.getString("key");

			CPDefinitionOptionRel cpDefinitionOptionRel =
				_cpDefinitionOptionRelLocalService.
					fetchCPDefinitionOptionRelByKey(cpDefinitionId, key);

			if (cpDefinitionOptionRel == null) {
				continue;
			}

			JSONArray valueJSONArray = jsonObject.getJSONArray("value");

			for (int j = 0; j < valueJSONArray.length(); j++) {
				String value = valueJSONArray.getString(j);

				CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
					_cpDefinitionOptionValueRelLocalService.
						fetchCPDefinitionOptionValueRel(
							cpDefinitionOptionRel.getCPDefinitionOptionRelId(),
							value);

				if (cpDefinitionOptionValueRel != null) {
					value = cpDefinitionOptionValueRel.getName(locale);
				}
				else {
					value = valueJSONArray.getString(j);
				}

				KeyValuePair keyValuePair = new KeyValuePair();

				keyValuePair.setKey(cpDefinitionOptionRel.getName(locale));
				keyValuePair.setValue(value);

				values.add(keyValuePair);
			}
		}

		return values;
	}

	@Override
	public DDMForm getPublicStoreDDMForm(
			long groupId, long commerceAccountId, long cpDefinitionId,
			Locale locale, boolean ignoreSKUCombinations,
			boolean skuContributor)
		throws PortalException {

		DDMForm ddmForm = _getDDMForm(
			cpDefinitionId, locale, ignoreSKUCombinations, skuContributor,
			false, true);

		if (!ignoreSKUCombinations) {
			ddmForm.addDDMFormRule(
				createDDMFormRule(
					ddmForm, groupId, commerceAccountId, cpDefinitionId));
		}

		return ddmForm;
	}

	@Override
	public String renderCPAttachmentFileEntryOptions(
			long cpDefinitionId, String json, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws PortalException {

		Locale locale = _portal.getLocale(renderRequest);

		DDMForm ddmForm = getCPAttachmentFileEntryDDMForm(
			cpDefinitionId, locale);

		return _render(
			cpDefinitionId, locale, ddmForm, json, renderRequest,
			renderResponse);
	}

	@Override
	public String renderCPInstanceOptions(
			long cpDefinitionId, String json, boolean ignoreSKUCombinations,
			boolean skuContributor, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws PortalException {

		Locale locale = _portal.getLocale(renderRequest);

		DDMForm ddmForm = getCPInstanceDDMForm(
			cpDefinitionId, locale, ignoreSKUCombinations, skuContributor);

		return _render(
			cpDefinitionId, locale, ddmForm, json, renderRequest,
			renderResponse);
	}

	@Override
	public String renderPublicStoreOptions(
			long cpDefinitionId, String json, boolean ignoreSKUCombinations,
			boolean skuContributor, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws PortalException {

		Locale locale = _portal.getLocale(renderRequest);

		CommerceAccount commerceAccount =
			_commerceAccountHelper.getCurrentCommerceAccount(
				_commerceChannelLocalService.
					getCommerceChannelGroupIdBySiteGroupId(
						_portal.getScopeGroupId(renderRequest)),
				_portal.getHttpServletRequest(renderRequest));

		long commerceAccountId = 0;

		if (commerceAccount != null) {
			commerceAccountId = commerceAccount.getCommerceAccountId();
		}

		DDMForm ddmForm = getPublicStoreDDMForm(
			_portal.getScopeGroupId(renderRequest), commerceAccountId,
			cpDefinitionId, locale, ignoreSKUCombinations, skuContributor);

		return _render(
			cpDefinitionId, locale, ddmForm, json, renderRequest,
			renderResponse);
	}

	protected DDMFormRule createDDMFormRule(
		DDMForm ddmForm, long groupId, long commerceAccountId,
		long cpDefinitionId) {

		String action = createDDMFormRuleAction(
			ddmForm, groupId, commerceAccountId, cpDefinitionId);

		return new DDMFormRule("TRUE", action);
	}

	/**
	 * Create a DDM form rule action as a call function, e.g.
	 * <pre>
	 * call(
	 * 	'getCPInstanceOptionsValues',
	 * 	concat(
	 * 		'cpDefinitionId=56698', ';', '56703=', getValue('56703'), ';',
	 * 		'56706=', getValue('56706')),
	 * 	'56703=color;56706=size')
	 * </pre>
	 */
	protected String createDDMFormRuleAction(
		DDMForm ddmForm, long groupId, long commerceAccountId,
		long cpDefinitionId) {

		String callFunctionStatement =
			"call('getCPInstanceOptionsValues', concat(%s), '%s')";

		return String.format(
			callFunctionStatement,
			createDDMFormRuleInputMapping(
				ddmForm, groupId, commerceAccountId, cpDefinitionId),
			createDDMFormRuleOutputMapping(ddmForm));
	}

	protected String createDDMFormRuleInputMapping(
		DDMForm ddmForm, long groupId, long commerceAccountId,
		long cpDefinitionId) {

		// The input information will be transformed in parameter request of
		// DDMDataProviderRequest class and it'll be accessible in the data
		// provider implementation.

		String inputMappingStatement = "'%s=', getValue('%s')";
		String delimiter = ", ';',";

		List<DDMFormField> ddmFormFields = ddmForm.getDDMFormFields();

		Stream<DDMFormField> stream = ddmFormFields.stream();

		Stream<String> inputMappingStatementStream = stream.map(
			field -> String.format(
				inputMappingStatement, field.getName(), field.getName()));

		inputMappingStatementStream = Stream.concat(
			Stream.of(
				String.format(
					"'cpDefinitionId=%s'", String.valueOf(cpDefinitionId))),
			inputMappingStatementStream);

		inputMappingStatementStream = Stream.concat(
			Stream.of(String.format("'groupId=%s'", String.valueOf(groupId))),
			inputMappingStatementStream);

		inputMappingStatementStream = Stream.concat(
			Stream.of(
				String.format(
					"'commerceAccountId=%s'",
					String.valueOf(commerceAccountId))),
			inputMappingStatementStream);

		return inputMappingStatementStream.collect(
			Collectors.joining(delimiter));
	}

	protected String createDDMFormRuleOutputMapping(DDMForm ddmForm) {
		String outputMappingStatement = "%s=%s";

		List<DDMFormField> ddmFormFields = ddmForm.getDDMFormFields();

		Stream<DDMFormField> stream = ddmFormFields.stream();

		Stream<String> stringStream = stream.map(
			field -> String.format(
				outputMappingStatement, field.getName(), field.getName()));

		return stringStream.collect(Collectors.joining(StringPool.SEMICOLON));
	}

	private DDMForm _getDDMForm(
			long cpDefinitionId, Locale locale, boolean ignoreSKUCombinations,
			boolean skuContributor, boolean optional, boolean publicStore)
		throws PortalException {

		List<CPDefinitionOptionRel> cpDefinitionOptionRels;

		if (skuContributor) {
			cpDefinitionOptionRels =
				_cpDefinitionOptionRelLocalService.getCPDefinitionOptionRels(
					cpDefinitionId, true);
		}
		else {
			cpDefinitionOptionRels =
				_cpDefinitionOptionRelLocalService.getCPDefinitionOptionRels(
					cpDefinitionId);
		}

		if (cpDefinitionOptionRels.isEmpty()) {
			return null;
		}

		DDMForm ddmForm = new DDMForm();

		Map<String, String> filters = new HashMap<>();

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinitionOptionRels) {

			if (Validator.isNull(
					cpDefinitionOptionRel.getDDMFormFieldTypeName())) {

				continue;
			}

			try {
				List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
					null;

				if (cpDefinitionOptionRel.isSkuContributor() && publicStore) {
					cpDefinitionOptionValueRels = getCPDefinitionOptionValueRel(
						cpDefinitionId, cpDefinitionOptionRel.getKey(),
						filters);
				}
				else {
					cpDefinitionOptionValueRels =
						cpDefinitionOptionRel.getCPDefinitionOptionValueRels();
				}

				DDMFormField ddmFormField = new DDMFormField(
					cpDefinitionOptionRel.getKey(),
					cpDefinitionOptionRel.getDDMFormFieldTypeName());

				if (!cpDefinitionOptionValueRels.isEmpty()) {
					DDMFormFieldOptions ddmFormFieldOptions =
						new DDMFormFieldOptions();

					for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
							cpDefinitionOptionValueRels) {

						ddmFormFieldOptions.addOptionLabel(
							cpDefinitionOptionValueRel.getKey(), locale,
							cpDefinitionOptionValueRel.getName(locale));
					}

					ddmFormField.setDDMFormFieldOptions(ddmFormFieldOptions);
				}

				LocalizedValue localizedValue = new LocalizedValue(locale);

				localizedValue.addString(
					locale, cpDefinitionOptionRel.getName(locale));

				ddmFormField.setLabel(localizedValue);

				boolean required = _isDDMFormRequired(
					cpDefinitionOptionRel, ignoreSKUCombinations, optional,
					publicStore);

				ddmFormField.setRequired(required);

				ddmForm.addDDMFormField(ddmFormField);
			}
			catch (Exception e) {
				throw new PortalException("Unable to find option values", e);
			}
		}

		ddmForm.addAvailableLocale(locale);
		ddmForm.setDefaultLocale(locale);

		return ddmForm;
	}

	private boolean _isDDMFormRequired(
		CPDefinitionOptionRel cpDefinitionOptionRel,
		boolean ignoreSKUCombinations, boolean optional, boolean publicStore) {

		if (optional) {
			return false;
		}

		Map<String, Object> properties =
			_ddmFormFieldTypeServicesTracker.getDDMFormFieldTypeProperties(
				cpDefinitionOptionRel.getDDMFormFieldTypeName());

		String fieldTypeDataDomain = MapUtil.getString(
			properties, "ddm.form.field.type.data.domain");

		if (Validator.isNotNull(fieldTypeDataDomain) &&
			fieldTypeDataDomain.equals("list")) {

			int cpDefinitionOptionValueRelsCount =
				_cpDefinitionOptionValueRelLocalService.
					getCPDefinitionOptionValueRelsCount(
						cpDefinitionOptionRel.getCPDefinitionOptionRelId());

			if (cpDefinitionOptionValueRelsCount == 0) {
				return false;
			}
		}

		if (ignoreSKUCombinations) {
			return cpDefinitionOptionRel.isRequired();
		}

		if (publicStore) {
			if (cpDefinitionOptionRel.isRequired() ||
				cpDefinitionOptionRel.isSkuContributor()) {

				return true;
			}
		}
		else {
			return cpDefinitionOptionRel.isSkuContributor();
		}

		return false;
	}

	private String _render(
			long cpDefinitionId, Locale locale, DDMForm ddmForm, String json,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortalException {

		if (ddmForm == null) {
			return StringPool.BLANK;
		}

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			renderRequest);

		HttpServletResponse httpServletResponse =
			_portal.getHttpServletResponse(renderResponse);

		DDMFormRenderingContext ddmFormRenderingContext =
			new DDMFormRenderingContext();

		ddmFormRenderingContext.setContainerId(
			"ProductOptions" + String.valueOf(cpDefinitionId));
		ddmFormRenderingContext.setHttpServletRequest(httpServletRequest);
		ddmFormRenderingContext.setHttpServletResponse(httpServletResponse);
		ddmFormRenderingContext.setLocale(locale);
		ddmFormRenderingContext.setPortletNamespace(
			renderResponse.getNamespace());
		ddmFormRenderingContext.setShowRequiredFieldsWarning(false);

		if (Validator.isNotNull(json)) {
			DDMFormValues ddmFormValues = _ddmFormValuesHelper.deserialize(
				ddmForm, json, locale);

			if (ddmFormValues != null) {
				ddmFormRenderingContext.setDDMFormValues(ddmFormValues);
			}
		}

		return _ddmFormRenderer.render(ddmForm, ddmFormRenderingContext);
	}

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceMediaResolver _commerceMediaResolver;

	@Reference
	private CPAttachmentFileEntryLocalService
		_cpAttachmentFileEntryLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPDefinitionOptionRelLocalService
		_cpDefinitionOptionRelLocalService;

	@Reference
	private CPDefinitionOptionValueRelLocalService
		_cpDefinitionOptionValueRelLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private DDMFormFieldTypeServicesTracker _ddmFormFieldTypeServicesTracker;

	@Reference
	private DDMFormRenderer _ddmFormRenderer;

	@Reference
	private DDMFormValuesHelper _ddmFormValuesHelper;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}