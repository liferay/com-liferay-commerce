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

package com.liferay.commerce.product.catalog;

import java.util.List;

/**
 * @author Marco Leo
 */
public interface CPCatalogEntry {

	public long getCPDefinitionId();

	public long getCProductId();

	public List<CPSku> getCPSkus();

	public String getDefaultImageFileUrl();

	public double getDepth();

	public String getDescription();

	public long getGroupId();

	public double getHeight();

	public String getMetaDescription(String languageId);

	public String getMetaKeywords(String languageId);

	public String getMetaTitle(String languageId);

	public String getName();

	public String getProductTypeName();

	public String getShortDescription();

	public String getUrl();

	public boolean isIgnoreSKUCombinations();

}