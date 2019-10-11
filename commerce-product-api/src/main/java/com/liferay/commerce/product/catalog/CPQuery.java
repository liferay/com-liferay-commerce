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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco Leo
 */
public class CPQuery {

	public static final String[] ORDER_BY_COLUMNS = {
		"name", "createDate", "modifiedDate", "publishDate", "expirationDate",
		"priority"
	};

	public static String checkOrderByCol(String orderByCol) {
		if (ArrayUtil.contains(ORDER_BY_COLUMNS, orderByCol) ||
			(orderByCol != null)) {

			return orderByCol;
		}

		return ORDER_BY_COLUMNS[2];
	}

	public static String checkOrderByType(String orderByType) {
		if ((orderByType == null) ||
			StringUtil.equalsIgnoreCase(orderByType, "DESC")) {

			return "DESC";
		}

		return "ASC";
	}

	public void addAllTagIdsArray(long[] allTagsIds) {
		if (allTagsIds.length == 0) {
			return;
		}

		_allTagIdsArray = ArrayUtil.append(_allTagIdsArray, allTagsIds);

		_allTagIds = _flattenTagIds(_allTagIdsArray);
	}

	public void addNotAllTagIdsArray(long[] notAllTagsIds) {
		if (notAllTagsIds.length == 0) {
			return;
		}

		_notAllTagIdsArray = ArrayUtil.append(
			_notAllTagIdsArray, notAllTagsIds);

		_notAllTagIds = _flattenTagIds(_notAllTagIdsArray);
	}

	public long[] getAllCategoryIds() {
		return ArrayUtil.clone(_allCategoryIds);
	}

	public long[] getAllLeftAndRightCategoryIds() {
		return _getLeftAndRightCategoryIds(ArrayUtil.clone(_allCategoryIds));
	}

	public long[] getAllTagIds() {
		return ArrayUtil.clone(_allTagIds);
	}

	public long[][] getAllTagIdsArray() {
		return ArrayUtil.clone(_allTagIdsArray);
	}

	public long[] getAnyCategoryIds() {
		return ArrayUtil.clone(_anyCategoryIds);
	}

	public long[] getAnyLeftAndRightCategoryIds() {
		return _getLeftAndRightCategoryIds(ArrayUtil.clone(_anyCategoryIds));
	}

	public long[] getAnyTagIds() {
		return ArrayUtil.clone(_anyTagIds);
	}

	public Serializable getAttribute(String name) {
		return _attributes.get(name);
	}

	public Map<String, Serializable> getAttributes() {
		return _attributes;
	}

	public long[] getNotAllCategoryIds() {
		return ArrayUtil.clone(_notAllCategoryIds);
	}

	public long[] getNotAllLeftAndRightCategoryIds() {
		return _getLeftAndRightCategoryIds(ArrayUtil.clone(_notAllCategoryIds));
	}

	public long[] getNotAllTagIds() {
		return ArrayUtil.clone(_notAllTagIds);
	}

	public long[][] getNotAllTagIdsArray() {
		return ArrayUtil.clone(_notAllTagIdsArray);
	}

	public long[] getNotAnyCategoryIds() {
		return ArrayUtil.clone(_notAnyCategoryIds);
	}

	public long[] getNotAnyLeftAndRightCategoryIds() {
		return _getLeftAndRightCategoryIds(ArrayUtil.clone(_notAnyCategoryIds));
	}

	public long[] getNotAnyTagIds() {
		return ArrayUtil.clone(_notAnyTagIds);
	}

	public String getOrderByCol1() {
		return checkOrderByCol(_orderByCol1);
	}

	public String getOrderByCol2() {
		return checkOrderByCol(_orderByCol2);
	}

	public String getOrderByType1() {
		return checkOrderByType(_orderByType1);
	}

	public String getOrderByType2() {
		return checkOrderByType(_orderByType2);
	}

	public void setAllCategoryIds(long[] allCategoryIds) {
		_allCategoryIds = ArrayUtil.clone(allCategoryIds);

		_toString = null;
	}

	public void setAllTagIds(long[] allTagIds) {
		_allTagIds = ArrayUtil.clone(allTagIds);

		_allTagIdsArray = _expandTagIds(allTagIds);

		_toString = null;
	}

	public void setAllTagIdsArray(long[][] allTagIdsArray) {
		_allTagIdsArray = ArrayUtil.clone(allTagIdsArray);

		_allTagIds = _flattenTagIds(ArrayUtil.clone(allTagIdsArray));

		_toString = null;
	}

	public void setAndOperator(boolean andOperator) {
		_andOperator = andOperator;
	}

	public void setAnyCategoryIds(long[] anyCategoryIds) {
		_anyCategoryIds = ArrayUtil.clone(anyCategoryIds);

		_toString = null;
	}

	public void setAnyTagIds(long[] anyTagIds) {
		_anyTagIds = ArrayUtil.clone(anyTagIds);

		_toString = null;
	}

	public void setAttribute(String name, Serializable value) {
		_attributes.put(name, value);
	}

	public void setAttributes(Map<String, Serializable> attributes) {
		if (_attributes == null) {
			_attributes = new HashMap<>();
		}
		else {
			_attributes = attributes;
		}
	}

	public void setNotAllCategoryIds(long[] notAllCategoryIds) {
		_notAllCategoryIds = ArrayUtil.clone(notAllCategoryIds);

		_toString = null;
	}

	public void setNotAllTagIds(long[] notAllTagIds) {
		_notAllTagIds = ArrayUtil.clone(notAllTagIds);

		_notAllTagIdsArray = _expandTagIds(ArrayUtil.clone(notAllTagIds));

		_toString = null;
	}

	public void setNotAllTagIdsArray(long[][] notAllTagIdsArray) {
		_notAllTagIdsArray = ArrayUtil.clone(notAllTagIdsArray);

		_notAllTagIds = _flattenTagIds(ArrayUtil.clone(notAllTagIdsArray));

		_toString = null;
	}

	public void setNotAnyCategoryIds(long[] notAnyCategoryIds) {
		_notAnyCategoryIds = ArrayUtil.clone(notAnyCategoryIds);

		_toString = null;
	}

	public void setNotAnyTagIds(long[] notAnyTagIds) {
		_notAnyTagIds = ArrayUtil.clone(notAnyTagIds);

		_toString = null;
	}

	public void setOrderByCol1(String orderByCol1) {
		_orderByCol1 = orderByCol1;

		_toString = null;
	}

	public void setOrderByCol2(String orderByCol2) {
		_orderByCol2 = orderByCol2;

		_toString = null;
	}

	public void setOrderByType1(String orderByType1) {
		_orderByType1 = orderByType1;

		_toString = null;
	}

	public void setOrderByType2(String orderByType2) {
		_orderByType2 = orderByType2;

		_toString = null;
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBundler sb = new StringBundler(27);

		sb.append("{allCategoryIds=");
		sb.append(StringUtil.merge(_allCategoryIds));
		sb.append(", allTagIds=");
		sb.append(StringUtil.merge(_allTagIds));
		sb.append(", andOperator=");
		sb.append(_andOperator);
		sb.append(", anyCategoryIds=");
		sb.append(StringUtil.merge(_anyCategoryIds));
		sb.append(", anyTagIds=");
		sb.append(StringUtil.merge(_anyTagIds));
		sb.append(", notAllCategoryIds=");
		sb.append(StringUtil.merge(_notAllCategoryIds));
		sb.append(", notAllTagIds=");
		sb.append(StringUtil.merge(_notAllTagIds));
		sb.append(", notAnyCategoryIds=");
		sb.append(StringUtil.merge(_notAnyCategoryIds));
		sb.append(", notAnyTagIds=");
		sb.append(StringUtil.merge(_notAnyTagIds));
		sb.append(", orderByCol1=");
		sb.append(_orderByCol1);
		sb.append(", orderByCol2=");
		sb.append(_orderByCol2);
		sb.append(", orderByType1=");
		sb.append(_orderByType1);
		sb.append(", orderByType2=");
		sb.append(_orderByType2);
		sb.append("}");

		_toString = sb.toString();

		return _toString;
	}

	private long[][] _expandTagIds(long[] tagIds) {
		long[][] tagIdsArray = new long[tagIds.length][1];

		for (int i = 0; i < tagIds.length; i++) {
			tagIdsArray[i][0] = tagIds[i];
		}

		return tagIdsArray;
	}

	private long[] _flattenTagIds(long[][] tagIdsArray) {
		List<Long> tagIdsList = new ArrayList<>();

		for (long[] tagIds : tagIdsArray) {
			for (long tagId : tagIds) {
				tagIdsList.add(tagId);
			}
		}

		return ArrayUtil.toArray(tagIdsList.toArray(new Long[0]));
	}

	private long[] _getLeftAndRightCategoryIds(long[] categoryIds) {
		long[] leftRightIds = new long[categoryIds.length * 3];

		for (int i = 0; i < categoryIds.length; i++) {
			long categoryId = categoryIds[i];

			try {
				AssetCategory category =
					AssetCategoryLocalServiceUtil.getCategory(categoryId);

				leftRightIds[3 * i] = category.getGroupId();
				leftRightIds[3 * i + 1] = category.getLeftCategoryId();
				leftRightIds[3 * i + 2] = category.getRightCategoryId();
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Error retrieving category " + categoryId);
				}
			}
		}

		return leftRightIds;
	}

	private static final Log _log = LogFactoryUtil.getLog(CPQuery.class);

	private long[] _allCategoryIds = new long[0];
	private long[] _allTagIds = new long[0];
	private long[][] _allTagIdsArray = new long[0][];
	private boolean _andOperator;
	private long[] _anyCategoryIds = new long[0];
	private long[] _anyTagIds = new long[0];
	private Map<String, Serializable> _attributes = new HashMap<>();
	private long[] _notAllCategoryIds = new long[0];
	private long[] _notAllTagIds = new long[0];
	private long[][] _notAllTagIdsArray = new long[0][];
	private long[] _notAnyCategoryIds = new long[0];
	private long[] _notAnyTagIds = new long[0];
	private String _orderByCol1;
	private String _orderByCol2;
	private String _orderByType1;
	private String _orderByType2;
	private String _toString;

}