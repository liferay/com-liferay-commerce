package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component(service = AddOrganizationsModalItemRenderer.class)
public class AddOrganizationsModalItemRenderer extends AbstractProductItemRenderer {

    private static final String COMPONENT_NAME = "add_organizations_modal";

    private static final String API_ENDPOINT =
            "/o/commerce-ui/search-organizations";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(AddOrganizationsModalItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();

        data.put("organizationsAPI", PortalUtil.getPortalURL(request) + API_ENDPOINT);
        data.put("query", StringPool.BLANK);
        data.put("spritemap", InfoItemRendererUtil.getSpritemapPath(request));

        return data;
    }
}
