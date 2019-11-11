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

package com.liferay.commerce.machine.learning.internal.recommendation.data.source;

import com.liferay.commerce.machine.learning.internal.recommendation.api.ProductCommerceMLRecommendationHelper;
import com.liferay.commerce.machine.learning.internal.recommendation.constants.CommerceMLRecommendationField;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPQuery;
import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.data.source.CPDataSource;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

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
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "commerce.product.data.source.name=" + ProductInteractionCommerceMLRecommendationCPDataSourceImpl.NAME,
	service = CPDataSource.class
)
public class ProductInteractionCommerceMLRecommendationCPDataSourceImpl
	extends BaseCommerceMLRecommendationCPDataSource {

	public static final String NAME =
		"productInteractionCommerceMLRecommendationDataSource";

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			getResourceBundle(locale),
			"product-interaction-based-recommendations");
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public CPDataSourceResult getResult(
			HttpServletRequest httpServletRequest, int start, int end)
		throws Exception {

		CPCatalogEntry cpCatalogEntry =
			(CPCatalogEntry)httpServletRequest.getAttribute(
				CPWebKeys.CP_CATALOG_ENTRY);

		if (cpCatalogEntry == null) {
			return new CPDataSourceResult(Collections.emptyList(), 0);
		}

		long companyId = _portal.getCompanyId(httpServletRequest);

		Hits recommendations =
			_productCommerceMLRecommendationHelper.getRecommendations(
				companyId, cpCatalogEntry.getCPDefinitionId());

		if (recommendations.getLength() == 0) {
			return new CPDataSourceResult(Collections.emptyList(), 0);
		}

		long groupId = _portal.getScopeGroupId(httpServletRequest);

		SearchContext searchContext = new SearchContext();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		searchContext.setAttributes(attributes);

		searchContext.setCompanyId(companyId);

		searchContext.setEntryClassNames(
			new String[] {CPDefinition.class.getName()});

		List<BooleanClause> booleanClauseList = new ArrayList<>();

		for (Document document : recommendations.getDocs()) {
			String recommendedEntryClassPK = document.get(
				CommerceMLRecommendationField.RECOMMENDED_ENTRY_CLASS_PK);

			String score = document.get(CommerceMLRecommendationField.SCORE);

			String rank = document.get(CommerceMLRecommendationField.RANK);

			if (_log.isTraceEnabled()) {
				StringBuilder sb = new StringBuilder();

				sb.append("Recommended item: ");
				sb.append(recommendedEntryClassPK);
				sb.append(" rank: ");
				sb.append(rank);
				sb.append(" score: ");
				sb.append(score);

				_log.trace(sb.toString());
			}

			BooleanClause<Query> entryClassPKBooleanClause =
				BooleanClauseFactoryUtil.create(
					Field.ENTRY_CLASS_PK, recommendedEntryClassPK,
					BooleanClauseOccur.SHOULD.getName());

			booleanClauseList.add(entryClassPKBooleanClause);
		}

		searchContext.setBooleanClauses(
			booleanClauseList.toArray(new BooleanClause[0]));

		return _cpDefinitionHelper.search(
			groupId, searchContext, new CPQuery(), start, end);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductInteractionCommerceMLRecommendationCPDataSourceImpl.class);

	@Reference(unbind = "-")
	private CPDefinitionHelper _cpDefinitionHelper;

	@Reference(unbind = "-")
	private Portal _portal;

	@Reference(
		target = "(component.name=com.liferay.commerce.machine.learning.internal.recommendation.ProductInteractionCommerceMLRecommendationHelperImpl)"
	)
	private ProductCommerceMLRecommendationHelper
		_productCommerceMLRecommendationHelper;

}