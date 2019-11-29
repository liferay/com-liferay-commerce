package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.util.ItemRendererUtil;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = SearchResultsItemRenderer.class)
public class SearchResultsItemRenderer extends BaseSoyProductItemRenderer {

	@Override
	protected String getComponentName() {
		return COMPONENT_NAME;
	}

	@Override
	protected Log getLogger() {
		return LogFactoryUtil.getLog(SearchResultsItemRenderer.class);
	}

	@Override
	protected Map<String, Object> getRenderingData(
			CPCatalogEntry cpCatalogEntry, HttpServletRequest request)
		throws PortalException {

		Map<String, Object> data = new HashMap<>();

		data.put("queryString", StringPool.BLANK);
		data.put("searchAPI", PortalUtil.getPortalURL(request) + API_ENDPOINT);
		data.put("spritemap", ItemRendererUtil.getSpritemapPath(request));
		data.put("visible", false);

		CommerceContext commerceContext = ItemRendererUtil.getCommerceContext(
			request);

		CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

		if (commerceAccount != null) {
			data.put(
				"commerceAccountId", commerceAccount.getCommerceAccountId());
		}

		return data;
	}

	private static final String API_ENDPOINT = "/o/commerce-ui/search/";

	private static final String COMPONENT_NAME = "search_results";

}