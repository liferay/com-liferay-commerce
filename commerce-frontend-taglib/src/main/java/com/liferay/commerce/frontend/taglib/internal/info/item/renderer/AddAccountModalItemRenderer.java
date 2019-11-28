package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.frontend.util.ItemRendererUtil;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component(service = AddAccountModalItemRenderer.class)
public class AddAccountModalItemRenderer extends BaseSoyProductItemRenderer {

    private static final String COMPONENT_NAME = "add_account_modal";

    private static final String API_ENDPOINT =
            "/o/commerce-ui/search-users";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(AddAccountModalItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(CPCatalogEntry cpCatalogEntry, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();

        data.put("usersAPI",
                PortalUtil.getPortalURL(request) + API_ENDPOINT);

        data.put("spritemap",
                ItemRendererUtil.getSpritemapPath(request));

        return data;
    }
}
