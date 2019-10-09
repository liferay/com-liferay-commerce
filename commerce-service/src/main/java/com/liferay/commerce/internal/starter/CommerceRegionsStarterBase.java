package com.liferay.commerce.internal.starter;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Riccardo Alberti
 */
public class CommerceRegionsStarterBase {

	protected void start(
			CommerceCountryLocalService commerceCountryLocalService,
			CommerceRegionLocalService commerceRegionLocalService,
			JSONFactory jsonFactory, ServiceContext serviceContext, int isoCode,
			String layoutsPath)
		throws Exception {

		init(
			commerceCountryLocalService, commerceRegionLocalService,
			jsonFactory);

		CommerceCountry commerceCountry = getCommerceCountry(
			serviceContext.getCompanyId(), isoCode);

		if (commerceCountry == null) {
			return;
		}

		parseAndAddCommerceRegions(
			commerceCountry, serviceContext, layoutsPath);
	}

	private CommerceCountry getCommerceCountry(long companyId, int isoCode)
		throws PortalException {

		return commerceCountryLocalService.fetchCommerceCountry(
			companyId, isoCode);
	}

	private JSONArray getCommerceRegionsJSONArray(String layoutsPath)
		throws Exception {

		Class<?> clazz = getClass();

		String regionsJSON = StringUtil.read(
			clazz.getClassLoader(), layoutsPath, false);

		return jsonFactory.createJSONArray(regionsJSON);
	}

	private void init(
		CommerceCountryLocalService commerceCountryLocalService,
		CommerceRegionLocalService commerceRegionLocalService,
		JSONFactory jsonFactory) {

		this.commerceCountryLocalService = commerceCountryLocalService;
		this.commerceRegionLocalService = commerceRegionLocalService;
		this.jsonFactory = jsonFactory;
	}

	private void parseAndAddCommerceRegions(
			CommerceCountry commerceCountry, ServiceContext serviceContext,
			String layoutsPath)
		throws Exception {

		JSONArray jsonArray = getCommerceRegionsJSONArray(layoutsPath);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String code = jsonObject.getString("code");
			String name = jsonObject.getString("name");
			double priority = jsonObject.getDouble("priority");

			commerceRegionLocalService.addCommerceRegion(
				commerceCountry.getCommerceCountryId(), name, code, priority,
				true, serviceContext);
		}
	}

	private CommerceCountryLocalService commerceCountryLocalService;
	private CommerceRegionLocalService commerceRegionLocalService;
	private JSONFactory jsonFactory;

}