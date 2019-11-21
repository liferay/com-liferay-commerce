package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(service = GalleryItemRenderer.class)
public class GalleryItemRenderer extends AbstractProductItemRenderer {

    private static final String COMPONENT_NAME = "gallery";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(GalleryItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(HttpServletRequest request) throws PortalException {
        Map<String, Object> data = new HashMap<>();

        ThemeDisplay themeDisplay = InfoItemRendererUtil.getThemeDisplay(request);
        long cpDefinitionId = (long) request.getAttribute("cpDefinitionId");

        data.put("selected", 0);
        data.put("images", _cpContentHelper.getImages(
                cpDefinitionId, themeDisplay));

        return data;
    }

    @Reference
    CPContentHelper _cpContentHelper;
}
