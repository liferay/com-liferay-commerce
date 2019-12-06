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

package com.liferay.commerce.product.internal.upgrade.v1_11_1;

import com.liferay.commerce.product.model.CPDisplayLayout;
import com.liferay.commerce.product.service.CPDisplayLayoutLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

/**
 * @author Riccardo Ferrari
 */
public class CPDisplayLayoutUpgradeProcess extends UpgradeProcess {

	public CPDisplayLayoutUpgradeProcess(
		CPDisplayLayoutLocalService cpDisplayLayoutLocalService,
		LayoutLocalService layoutLocalService) {

		_cpDisplayLayoutLocalService = cpDisplayLayoutLocalService;
		_layoutLocalService = layoutLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<CPDisplayLayout> cpDisplayLayouts =
			_cpDisplayLayoutLocalService.getCPDisplayLayouts(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (CPDisplayLayout cpDisplayLayout : cpDisplayLayouts) {
			Layout layout = _getLayout(cpDisplayLayout);

			if (layout == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						String.format(
							"Removing orphan CPDisplayLayout entry id: %s",
							cpDisplayLayout.getCPDisplayLayoutId()));
				}

				_cpDisplayLayoutLocalService.deleteCPDisplayLayout(
					cpDisplayLayout);
			}
		}
	}

	private Layout _getLayout(CPDisplayLayout cpDisplayLayout) {
		Layout layout = _layoutLocalService.fetchLayout(
			cpDisplayLayout.getLayoutUuid(), cpDisplayLayout.getGroupId(),
			false);

		if (layout != null) {
			return layout;
		}

		return _layoutLocalService.fetchLayout(
			cpDisplayLayout.getLayoutUuid(), cpDisplayLayout.getGroupId(),
			true);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPDisplayLayoutUpgradeProcess.class);

	private final CPDisplayLayoutLocalService _cpDisplayLayoutLocalService;
	private final LayoutLocalService _layoutLocalService;

}