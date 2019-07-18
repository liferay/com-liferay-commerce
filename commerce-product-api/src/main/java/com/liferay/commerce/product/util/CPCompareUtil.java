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

package com.liferay.commerce.product.util;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Alessio Antonio Rendina
 */
public class CPCompareUtil {

	public static void addCompareProduct(
		long groupId, HttpSession httpSession, long cpDefinitionId) {

		List<Long> cpDefinitionIds = getCPDefinitionIds(groupId, httpSession);

		if (!cpDefinitionIds.contains(cpDefinitionId)) {
			cpDefinitionIds.add(cpDefinitionId);
		}

		setCPDefinitionIds(groupId, httpSession, cpDefinitionIds);
	}

	public static List<Long> getCPDefinitionIds(
		long groupId, HttpSession httpSession) {

		List<Long> cpDefinitionIds = (List<Long>)httpSession.getAttribute(
			_getSessionAttributeKey(groupId));

		if (cpDefinitionIds == null) {
			return new ArrayList<>();
		}

		return cpDefinitionIds;
	}

	public static void removeCompareProduct(
			long groupId, HttpSession httpSession, long cpDefinitionId)
		throws PortalException {

		List<Long> cpDefinitionIds = getCPDefinitionIds(groupId, httpSession);

		if (cpDefinitionIds.contains(cpDefinitionId)) {
			cpDefinitionIds.remove(cpDefinitionId);
		}

		setCPDefinitionIds(groupId, httpSession, cpDefinitionIds);
	}

	public static void setCPDefinitionIds(
		long groupId, HttpSession httpSession, List<Long> cpDefinitionIds) {

		httpSession.setAttribute(
			_getSessionAttributeKey(groupId), cpDefinitionIds);
	}

	private static String _getSessionAttributeKey(long groupId) {
		return _SESSION_COMPARE_CP_DEFINITION_IDS + groupId;
	}

	private static final String _SESSION_COMPARE_CP_DEFINITION_IDS =
		"LIFERAY_SHARED_CP_DEFINITION_IDS_";

}