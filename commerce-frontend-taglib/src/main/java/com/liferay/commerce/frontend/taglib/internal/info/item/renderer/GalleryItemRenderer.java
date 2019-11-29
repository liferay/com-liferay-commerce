package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.frontend.util.ItemRendererUtil;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = GalleryItemRenderer.class)
public class GalleryItemRenderer extends BaseSoyProductItemRenderer {

	@Override
	protected String getComponentName() {
		return COMPONENT_NAME;
	}

	@Override
	protected Log getLogger() {
		return LogFactoryUtil.getLog(GalleryItemRenderer.class);
	}

	@Override
	protected Map<String, Object> getRenderingData(
			CPCatalogEntry cpCatalogEntry, HttpServletRequest request)
		throws PortalException {

		Map<String, Object> data = new HashMap<>();

		ThemeDisplay themeDisplay = ItemRendererUtil.getThemeDisplay(request);

		data.put(
			"images",
			_cpContentHelper.getImages(
				_getCPDefinitionId(cpCatalogEntry, request), themeDisplay));
		data.put("selected", 0);

		return data;
	}

	private long _getCPDefinitionId(
		CPCatalogEntry cpCatalogEntry, HttpServletRequest request) {

		return Optional.of(
			(long)request.getAttribute("cpDefinitionId")
		).orElse(
			cpCatalogEntry.getCPDefinitionId()
		);
	}

	private static final String COMPONENT_NAME = "gallery";

	@Reference
	CPContentHelper _cpContentHelper;

}