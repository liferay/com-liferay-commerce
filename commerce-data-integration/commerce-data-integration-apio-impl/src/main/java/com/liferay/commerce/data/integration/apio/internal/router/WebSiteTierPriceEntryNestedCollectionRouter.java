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

package com.liferay.commerce.data.integration.apio.internal.router;

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.router.NestedCollectionRouter;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.commerce.data.integration.apio.identifiers.CommerceTierPriceEntryIdentifier;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.site.apio.architect.identifier.WebSiteIdentifier;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the information necessary to expose a {@link CommerceTierPriceEntry}
 * resources contained inside a <a href="http://schema.org/WebSite">WebSite</a>
 * through a web API. The resources are mapped from the internal model {@link
 * CommerceTierPriceEntry}.
 *
 * @author Zoltán Takács
 * @review
 */
@Component(immediate = true)
public class WebSiteTierPriceEntryNestedCollectionRouter
	implements NestedCollectionRouter
		<CommerceTierPriceEntry, Long, CommerceTierPriceEntryIdentifier, Long,
		WebSiteIdentifier> {

	@Override
	public NestedCollectionRoutes<CommerceTierPriceEntry, Long, Long>
		collectionRoutes(
			NestedCollectionRoutes.Builder<CommerceTierPriceEntry, Long, Long>
				builder) {

		return builder.addGetter(
			this::_getPageItems
		).build();
	}

	private PageItems<CommerceTierPriceEntry> _getPageItems(
			Pagination pagination, long groupId)
		throws PortalException {

		List<CommerceTierPriceEntry> commercePriceEntries =
			_commerceTierPriceEntryService.fetchCommerceTierPriceEntries(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition());
		int count =
			_commerceTierPriceEntryService.
				getCommerceTierPriceEntriesCountByGroupId(groupId);

		return new PageItems<>(commercePriceEntries, count);
	}

	@Reference
	private CommerceTierPriceEntryService _commerceTierPriceEntryService;

}