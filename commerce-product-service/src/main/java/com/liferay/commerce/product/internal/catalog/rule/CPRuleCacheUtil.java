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

package com.liferay.commerce.product.internal.catalog.rule;

import com.liferay.petra.lang.HashUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheHelperUtil;
import com.liferay.portal.kernel.cache.PortalCacheManagerNames;
import com.liferay.portal.util.PropsValues;

import java.io.Serializable;

/**
 * @author Marco Leo
 */
public class CPRuleCacheUtil {

	public static final String CP_RULES_BAG_CACHE_NAME =
		CPRuleCacheUtil.class.getName() + "_CP_RULES_BAG";

	public static void clearCommerceAccountGroupCPRuleIds() {
		PortalCacheHelperUtil.removeAllWithoutReplicator(
			_accountGroupCPRuleIdsKeyPortalCache);
	}

	public static long[] getCommerceAccountGroupCPRuleIds(
		long commerceAccountId, long groupId) {

		AccountGroupCPRuleIdsKey accountGroupCPRuleIdsKey =
			new AccountGroupCPRuleIdsKey(commerceAccountId, groupId);

		return _accountGroupCPRuleIdsKeyPortalCache.get(
			accountGroupCPRuleIdsKey);
	}

	public static void putCommerceAccountGroupCPRuleIds(
		long commerceAccountId, long groupId,
		long[] commerceUserSegmentEntryIds) {

		if (commerceUserSegmentEntryIds == null) {
			return;
		}

		AccountGroupCPRuleIdsKey accountGroupCPRuleIdsKey =
			new AccountGroupCPRuleIdsKey(commerceAccountId, groupId);

		PortalCacheHelperUtil.putWithoutReplicator(
			_accountGroupCPRuleIdsKeyPortalCache, accountGroupCPRuleIdsKey,
			commerceUserSegmentEntryIds);
	}

	private static final PortalCache<AccountGroupCPRuleIdsKey, long[]>
		_accountGroupCPRuleIdsKeyPortalCache =
			PortalCacheHelperUtil.getPortalCache(
				PortalCacheManagerNames.MULTI_VM, CP_RULES_BAG_CACHE_NAME,
				PropsValues.PERMISSIONS_OBJECT_BLOCKING_CACHE);

	private static class AccountGroupCPRuleIdsKey implements Serializable {

		@Override
		public boolean equals(Object obj) {
			AccountGroupCPRuleIdsKey userAccountGroupCPRuleIdsKey =
				(AccountGroupCPRuleIdsKey)obj;

			if ((userAccountGroupCPRuleIdsKey._commerceAccountId ==
					_commerceAccountId) &&
				(userAccountGroupCPRuleIdsKey._groupId == _groupId)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			int hashCode = HashUtil.hash(0, _commerceAccountId);

			return HashUtil.hash(hashCode, _groupId);
		}

		private AccountGroupCPRuleIdsKey(long commerceAccountId, long groupId) {
			_commerceAccountId = commerceAccountId;
			_groupId = groupId;
		}

		private static final long serialVersionUID = 1L;

		private final long _commerceAccountId;
		private final long _groupId;

	}

}