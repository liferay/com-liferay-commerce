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

package com.liferay.commerce.product.internal.upgrade.v1_6_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Luca Pellizzon
 */
public class CPDefinitionTrashEntriesUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Class<CPDefinitionTrashEntriesUpgradeProcess> clazz =
			CPDefinitionTrashEntriesUpgradeProcess.class;

		//CPDefinition

		String template = "DELETE FROM trashentry WHERE classNameId = '%s';";

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(
			CPDefinition.class.getName());

		runSQLTemplateString(
			String.format(template, classNameId), false, false);

		//CPInstance

		classNameId = ClassNameLocalServiceUtil.getClassNameId(
			CPInstance.class.getName());

		runSQLTemplateString(
			String.format(template, classNameId), false, false);
	}

}