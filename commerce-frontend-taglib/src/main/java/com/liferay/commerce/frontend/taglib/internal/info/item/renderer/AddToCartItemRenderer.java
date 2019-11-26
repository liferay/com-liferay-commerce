package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.taglib.internal.info.item.renderer.util.InfoItemRendererUtil;
import com.liferay.commerce.frontend.util.ProductHelper;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Gianmarco Brunialti Masera
 */

@Component(service = AddToCartItemRenderer.class)
public class AddToCartItemRenderer extends BaseSoyProductItemRenderer {

    private static final String COMPONENT_NAME = "add_to_cart";

    private static final String API_ENDPOINT =
            "/o/commerce-ui/cart-item";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(AddToCartItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(CPCatalogEntry cpCatalogEntry, HttpServletRequest request)
            throws Exception {

        Map<String, Object> data = new HashMap<>();

        _cpInstanceId = _getCPInstanceId(cpCatalogEntry);

        CommerceContext commerceContext = InfoItemRendererUtil.getCommerceContext(request);

        CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

        CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

        data.put("cartAPI", PortalUtil.getPortalURL(request) + API_ENDPOINT);
        data.put("portletId", request.getAttribute(WebKeys.PORTLET_ID));
        data.put("editMode", false);
        data.put("productId", _cpInstanceId);

        if (commerceAccount != null) {
            data.put("accountId", commerceAccount.getCommerceAccountId());
        }

        int productOrderQuantity = 0;

        if (commerceOrder != null) {
            data.put("orderId", commerceOrder.getCommerceOrderId());

            productOrderQuantity = commerceOrder
                    .getCommerceOrderItemsCount(_cpInstanceId);
        }

        data.put("quantity", productOrderQuantity);

        data.put("settings", _productHelper.getProductSettingsModel(_cpInstanceId));

        return data;
    }

    @Override
    protected String getComponentId(HttpServletRequest request) {
        Optional<String> componentId = Optional.ofNullable(
                (String) request.getAttribute("componentId"));

        return componentId.orElse(
                _cpInstanceId + "AddToCartButtonId");
    }

    private long _getCPInstanceId(CPCatalogEntry cpCatalogEntry) throws Exception {
        return _cpContentHelper.getDefaultCPSku(cpCatalogEntry).getCPInstanceId();
    }

    @Reference
    private CPContentHelper _cpContentHelper;

    @Reference
    private ProductHelper _productHelper;

    private long _cpInstanceId;
}