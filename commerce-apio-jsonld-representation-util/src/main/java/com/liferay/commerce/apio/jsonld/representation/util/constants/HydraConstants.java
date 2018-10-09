/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.apio.jsonld.representation.util.constants;

/**
 * Hydra Vocabulary for Hypermedia-Driven Web APIs, see <a
 * href="https://www.w3.org/ns/hydra/core">Hydra Core Vocabulary</a>
 *
 * @author Zoltán Takács
 */
public interface HydraConstants {

	public interface FieldNames {

		public static final String COLLECTION = "collection";

		public static final String DESCRIPTION = "description";

		public static final String EXPECTS = "expects";

		public static final String FIRST = "first";

		public static final String LAST = "last";

		public static final String MANAGES = "manages";

		public static final String MEMBER = "member";

		public static final String METHOD = "method";

		public static final String NEXT = "next";

		public static final String NUMBER_OF_ITEMS = "numberOfItems";

		public static final String OBJECT = "object";

		public static final String OPERATION = "operation";

		public static final String PREVIOUS = "previous";

		public static final String PROPERTY = "property";

		public static final String READABLE = "readable";

		public static final String REQUIRED = "required";

		public static final String SUPPORTED_CLASS = "supportedClass";

		public static final String SUPPORTED_PROPERTY = "supportedProperty";

		public static final String TITLE = "title";

		public static final String TOTAL_ITEMS = "totalItems";

		public static final String VIEW = "view";

		public static final String WRITEABLE = "writeable";

	}

	public interface FieldTypes {

		public static final String API_DOCUMENTATION = "ApiDocumentation";

		public static final String CLASS = "Class";

		public static final String COLLECTION = "Collection";

		public static final String ENTRY_POINT = "EntryPoint";

		public static final String PARTIAL_COLLECTION_VIEW =
			"PartialCollectionView";

		public static final String SUPPORTED_PROPERTY = "SupportedProperty";

	}

}