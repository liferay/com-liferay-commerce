package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component(service = AddressModalItemRenderer.class)
public class AddressModalItemRenderer extends AbstractProductItemRenderer {

    private static final String COMPONENT_NAME = "address_modal";

    private static final String REGIONS_API_ENDPOINT =
            "/o/commerce-ui/address/regions/";
    private static final String COUNTRIES_API_ENDPOINT =
            "/o/commerce-ui/address/countries/";
    private static final String COUNTRIES_BY_CHANNEL_API_ENDPOINT =
            "/o/commerce-ui/address/countries-by-channel-id/";


    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(AddressModalItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();

        data.put("countriesAPI", _resolveCountriesEndpoint(request));
        data.put("regionsAPI", PortalUtil.getPortalURL(request) + REGIONS_API_ENDPOINT);
        data.put("spritemap", InfoItemRendererUtil.getSpritemapPath(request));

        return data;
    }

    private String _resolveCountriesEndpoint(HttpServletRequest request) {
        String portalURL = PortalUtil.getPortalURL(request);
        String authToken = AuthTokenUtil.getToken(request);

        String countriesEndpoint;

        try {
            CommerceContext commerceContext =
                    (CommerceContext) request.getAttribute(
                            CommerceWebKeys.COMMERCE_CONTEXT);

            countriesEndpoint = StringBundler.concat(
                    portalURL, COUNTRIES_BY_CHANNEL_API_ENDPOINT,
                    "?channelId=", String.valueOf(commerceContext.getCommerceChannelId()),
                    "&p_auth=", authToken);
        } catch (PortalException pe) {
            getLogger().error(pe, pe);

            countriesEndpoint = StringBundler.concat(
                    portalURL, COUNTRIES_API_ENDPOINT,
                    "?p_auth=", AuthTokenUtil.getToken(request));
        }

        return countriesEndpoint;
    }
}
