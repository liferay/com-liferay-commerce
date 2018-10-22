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

package com.liferay.commerce.product.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Marco Leo
 * @generated
 */
@ProviderType
public interface CPInstanceFinder {
	public int countByC_NotCST_NotST(long cpDefinitionId,
		int cpDefinitionStatus, int cpInstanceStatus);

	public int countByC_NotCST_ST(long cpDefinitionId, int cpDefinitionStatus,
		int cpInstanceStatus);

	public java.util.List<com.liferay.commerce.product.model.CPInstance> findByExpirationDate(
		java.util.Date expirationDate,
		com.liferay.portal.kernel.dao.orm.QueryDefinition<com.liferay.commerce.product.model.CPInstance> queryDefinition);

	public java.util.List<com.liferay.commerce.product.model.CPInstance> findByC_NotCST_NotST(
		long cpDefinitionId, int cpDefinitionStatus, int cpInstanceStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPInstance> orderByComparator);

	public java.util.List<com.liferay.commerce.product.model.CPInstance> findByC_NotCST_ST(
		long cpDefinitionId, int cpDefinitionStatus, int cpInstanceStatus,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPInstance> orderByComparator);
}