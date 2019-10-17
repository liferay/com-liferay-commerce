package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.frontend.template.soy.renderer.ComponentDescriptor;
import com.liferay.commerce.frontend.template.soy.renderer.SoyComponentRenderer;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.info.renderer.InfoItemRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component(service = AddToCartItemRenderer.class)
public class AddToCartItemRenderer implements InfoItemRenderer<CPCatalogEntry> {

    private static final String MODULE_NAME =
            "commerce-frontend-taglib/add_to_cart/AddToCartButton.es";

    private static final String TEMPLATE_NAMESPACE = "AddToCartButton.render";
    private static final boolean IS_WRAPPER = true;
    private static final boolean RENDER_JAVASCRIPT = true;
    private static final boolean POSITION_INLINE = true;

    private static final String CART_ITEM_ENDPOINT =
            "/o/commerce-ui/cart-item";

    @Override
    public void render(CPCatalogEntry cpCatalogEntry,
                       HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) {

        try {
            CPSku cpSku = _cpContentHelper.getDefaultCPSku(cpCatalogEntry);
            long cpInstanceId = cpSku.getCPInstanceId();
            Map<String, Object> data = getRenderingData(httpServletRequest, cpInstanceId);
            Writer writer = httpServletResponse.getWriter();
            ComponentDescriptor soyComponentDescriptor = getDescriptor(httpServletRequest, cpInstanceId);

            _soyRenderer.renderSoyComponent(
                    httpServletRequest, writer, soyComponentDescriptor, data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> getRenderingData(HttpServletRequest request,
                                                 long cpInstanceId) throws PortalException {
        CommerceContext commerceContext =
                (CommerceContext)request.getAttribute(
                        CommerceWebKeys.COMMERCE_CONTEXT);

        CommerceAccount commerceAccount = commerceContext.getCommerceAccount();
        CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

        Map<String, Object> data = new HashMap<>();

        data.put("cartAPI", PortalUtil.getPortalURL(request) + CART_ITEM_ENDPOINT);
        data.put("portletId", request.getAttribute(WebKeys.PORTLET_ID));
        data.put("editMode", false);
        data.put("productId", cpInstanceId);

        if (commerceAccount != null) {
            data.put("accountId", commerceAccount.getCommerceAccountId());
        }

        int productOrderQuantity = 0;

        if (commerceOrder != null) {
            data.put("orderId", commerceOrder.getCommerceOrderId());

            productOrderQuantity = commerceOrder
                    .getCommerceOrderItemsCount(cpInstanceId);
        }

        data.put("quantity", productOrderQuantity);

        return data;
    }

    private ComponentDescriptor getDescriptor(HttpServletRequest request, long cpInstanceId) {
        return new ComponentDescriptor(TEMPLATE_NAMESPACE,
                _npmResolver.resolveModuleName(MODULE_NAME),
                getComponentId(request, cpInstanceId),
                null, IS_WRAPPER,
                RENDER_JAVASCRIPT, POSITION_INLINE
        );
    }

    private String getComponentId(HttpServletRequest request, long cpInstanceId) {
        Optional<String> componentId = Optional.ofNullable((String) request.getAttribute("id"));
        String defaultComponentId = cpInstanceId + "AddToCartButtonId";

        return componentId.orElse(defaultComponentId);
    }


    @Reference
    private NPMResolver _npmResolver;

    @Reference
    private SoyComponentRenderer _soyRenderer;

    @Reference
    private CPContentHelper _cpContentHelper;
}