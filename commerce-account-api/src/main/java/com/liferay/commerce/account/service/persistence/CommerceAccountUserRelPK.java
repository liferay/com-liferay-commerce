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

package com.liferay.commerce.account.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Marco Leo
 * @generated
 */
@ProviderType
public class CommerceAccountUserRelPK implements Comparable<CommerceAccountUserRelPK>,
	Serializable {
	public long commerceAccountId;
	public long userId;

	public CommerceAccountUserRelPK() {
	}

	public CommerceAccountUserRelPK(long commerceAccountId, long userId) {
		this.commerceAccountId = commerceAccountId;
		this.userId = userId;
	}

	public long getCommerceAccountId() {
		return commerceAccountId;
	}

	public void setCommerceAccountId(long commerceAccountId) {
		this.commerceAccountId = commerceAccountId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int compareTo(CommerceAccountUserRelPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (commerceAccountId < pk.commerceAccountId) {
			value = -1;
		}
		else if (commerceAccountId > pk.commerceAccountId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (userId < pk.userId) {
			value = -1;
		}
		else if (userId > pk.userId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceAccountUserRelPK)) {
			return false;
		}

		CommerceAccountUserRelPK pk = (CommerceAccountUserRelPK)obj;

		if ((commerceAccountId == pk.commerceAccountId) &&
				(userId == pk.userId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, commerceAccountId);
		hashCode = HashUtil.hash(hashCode, userId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("commerceAccountId=");

		sb.append(commerceAccountId);
		sb.append(", userId=");

		sb.append(userId);

		sb.append("}");

		return sb.toString();
	}
}