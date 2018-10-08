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

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.commerce.price.list.exception.NoSuchPriceEntryException;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryService;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringBundler;

import java.math.BigDecimal;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = CommerceTierPriceEntryHelper.class)
public class CommerceTierPriceEntryHelper {

	public CommerceTierPriceEntry getCommerceTierPriceEntry(
			Long commerceTierPriceEntryId)
		throws PortalException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			_commerceTierPriceEntryService.fetchCommerceTierPriceEntry(
				commerceTierPriceEntryId);

		if (commerceTierPriceEntry == null) {
			throw new NotFoundException(
				"Unable to find tier price entry with ID " +
					commerceTierPriceEntryId);
		}

		return commerceTierPriceEntry;
	}

	public CommerceTierPriceEntry updateCommerceTierPriceEntry(
			Long commerceTierPriceEntryId, Long minQuantity, Double price,
			Double promoPrice)
		throws PortalException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			getCommerceTierPriceEntry(commerceTierPriceEntryId);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceTierPriceEntry.getGroupId());

		return _commerceTierPriceEntryService.updateCommerceTierPriceEntry(
			commerceTierPriceEntryId, BigDecimal.valueOf(price),
			BigDecimal.valueOf(promoPrice), minQuantity.intValue(),
			serviceContext);
	}

	public CommerceTierPriceEntry upsertCommerceTierPriceEntry(
			Long commerceTierPriceEntryId, Long commercePriceEntryId,
			Long minQuantity, Double price, Double promoPrice,
			String externalReferenceCode,
			String priceEntryExternalReferenceCode)
		throws PortalException {

		long groupId = _getGroupId(
			commercePriceEntryId, priceEntryExternalReferenceCode);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		return _commerceTierPriceEntryService.upsertCommerceTierPriceEntry(
			commerceTierPriceEntryId, commercePriceEntryId,
			externalReferenceCode, BigDecimal.valueOf(price),
			BigDecimal.valueOf(promoPrice), minQuantity.intValue(),
			priceEntryExternalReferenceCode, serviceContext);
	}

	private long _getGroupId(
			Long commercePriceEntryId, String priceEntryExternalReferenceCode)
		throws PortalException {

		if (commercePriceEntryId > 0) {
			CommercePriceEntry commercePriceEntry =
				_commercePriceEntryService.fetchCommercePriceEntry(
					commercePriceEntryId);

			if (commercePriceEntry != null) {
				return commercePriceEntry.getGroupId();
			}
		}

		if (priceEntryExternalReferenceCode != null) {
			CommercePriceEntry commercePriceEntry =
				_commercePriceEntryService.fetchByExternalReferenceCode(
					CompanyThreadLocal.getCompanyId(),
					priceEntryExternalReferenceCode);

			if (commercePriceEntry != null) {
				return commercePriceEntry.getGroupId();
			}
		}

		StringBundler sb = new StringBundler(6);

		sb.append("{commercePriceEntryId=");
		sb.append(commercePriceEntryId);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("priceEntryExternalReferenceCode=");
		sb.append(priceEntryExternalReferenceCode);
		sb.append(CharPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceEntryException(sb.toString());
	}

	@Reference
	private CommercePriceEntryService _commercePriceEntryService;

	@Reference
	private CommerceTierPriceEntryService _commerceTierPriceEntryService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}