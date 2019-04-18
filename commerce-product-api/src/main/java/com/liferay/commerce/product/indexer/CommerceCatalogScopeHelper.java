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

package com.liferay.commerce.product.indexer;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;

import java.util.Map;

/**
 * @author Ethan Bustad
 */
@ProviderType
public interface CommerceCatalogScopeHelper {

	public static final String COMMERCE_CATALOG_SCOPE_DOCUMENT_TYPE =
		"CommerceCatalogScopeDocumentType";

	public boolean deleteDocument(AuditedModel auditedModel);

	public int getDefaultFetchSize();

	public Sort[] getDefaultSorts();

	public boolean reindex(AuditedModel auditedModel);

	public Hits search(long companyId, Map<String, String> parameters)
		throws SearchException;

	public Hits search(
			long companyId, Map<String, String> parameters, int start, int end)
		throws SearchException;

	public Hits search(
			long companyId, Map<String, String> parameters, int start, int end,
			Sort[] sorts)
		throws SearchException;

}