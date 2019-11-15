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

package com.liferay.commerce.bom.rest.internal.resource.v1_0;

import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.commerce.bom.model.CommerceBOMEntry;
import com.liferay.commerce.bom.rest.dto.v1_0.Area;
import com.liferay.commerce.bom.rest.dto.v1_0.AreaData;
import com.liferay.commerce.bom.rest.dto.v1_0.Product;
import com.liferay.commerce.bom.rest.dto.v1_0.Spot;
import com.liferay.commerce.bom.rest.internal.dto.v1_0.converter.util.BreadcrumbDTOConverterUtil;
import com.liferay.commerce.bom.rest.resource.v1_0.AreaResource;
import com.liferay.commerce.bom.service.CommerceBOMDefinitionService;
import com.liferay.commerce.bom.service.CommerceBOMEntryService;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/area.properties",
	scope = ServiceScope.PROTOTYPE, service = AreaResource.class
)
public class AreaResourceImpl extends BaseAreaResourceImpl {

	@Override
	public Area getArea(Long id) throws Exception {
		CommerceBOMDefinition commerceBOMDefinition =
			_commerceBOMDefinitionService.getCommerceBOMDefinition(id);

		DTOConverter breadcrumbDTOConverter =
			_dtoConverterRegistry.getDTOConverter("breadcrumb");

		Area area = new Area();

		AreaData areaData = new AreaData();

		CPAttachmentFileEntry cpAttachmentFileEntry =
			commerceBOMDefinition.fetchCPAttachmentFileEntry();

		FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

		String url = DLUtil.getDownloadURL(
			fileEntry, fileEntry.getFileVersion(), null, null);

		areaData.setId(
			String.valueOf(commerceBOMDefinition.getCommerceBOMDefinitionId()));
		areaData.setImageUrl(url);

		areaData.setName(commerceBOMDefinition.getName());

		area.setBreadcrumbs(
			BreadcrumbDTOConverterUtil.getBreadcrumbs(
				breadcrumbDTOConverter,
				commerceBOMDefinition.fetchCommerceBOMFolder(),
				contextAcceptLanguage.getPreferredLocale()));

		Spot[] spots = _getSpots(
			commerceBOMDefinition.getCommerceBOMDefinitionId());

		areaData.setProducts(_getProducts(spots));
		areaData.setSpots(spots);

		area.setData(areaData);

		return area;
	}

	private Product[] _getProducts(Spot[] spots) throws Exception {
		List<Product> productList = new ArrayList<>();

		DTOConverter productDTOConverter =
			_dtoConverterRegistry.getDTOConverter("commerceProductInstance");

		for (Spot spot : spots) {
			CProduct cProduct =
				_cProductLocalService.getCProductByCPInstanceUuid(
					spot.getProductId());

			CPInstance cpInstance = _cpInstanceLocalService.getCProductInstance(
				cProduct.getCProductId(), spot.getProductId());

			productList.add(
				(Product)productDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						cpInstance.getCPInstanceId())));
		}

		Product[] products = new Product[productList.size()];

		return productList.toArray(products);
	}

	private Spot[] _getSpots(long areaId) throws Exception {
		List<Spot> spotList = new ArrayList<>();

		DTOConverter spotDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CommerceBOMEntry.class.getName());

		List<CommerceBOMEntry> commerceBOMEntries =
			_commerceBOMEntryService.getCommerceBOMEntries(
				areaId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (CommerceBOMEntry commerceBOMEntry : commerceBOMEntries) {
			spotList.add(
				(Spot)spotDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceBOMEntry.getCommerceBOMEntryId())));
		}

		Spot[] spots = new Spot[spotList.size()];

		return spotList.toArray(spots);
	}

	@Reference
	private CommerceBOMDefinitionService _commerceBOMDefinitionService;

	@Reference
	private CommerceBOMEntryService _commerceBOMEntryService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private CProductLocalService _cProductLocalService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}