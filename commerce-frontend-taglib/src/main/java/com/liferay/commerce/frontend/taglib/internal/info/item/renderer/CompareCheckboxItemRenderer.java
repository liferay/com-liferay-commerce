package com.liferay.commerce.frontend.taglib.internal.info.item.renderer;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.util.CPCompareHelperUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = CompareCheckboxItemRenderer.class)
public class CompareCheckboxItemRenderer extends AbstractProductItemRenderer {

    private static final String COMPONENT_NAME = "compare_checkbox";

    @Override
    protected String getComponentName() {
        return COMPONENT_NAME;
    }

    @Override
    protected Log getLogger() {
        return LogFactoryUtil.getLog(CompareCheckboxItemRenderer.class);
    }

    @Override
    protected Map<String, Object> getRenderingData(HttpServletRequest request) throws Exception {
        long cpDefinitionId = (long) request.getAttribute("cpDefinitionId");

        Map<String, Object> data = new HashMap<>();

        data.put("productId", cpDefinitionId);
        data.put("checkboxVisible", true);
        data.put("compareAvailable", true);
        data.put("inCompare", false);
        data.put("labelVisible", true);

        CPDefinition cpDefinition =
                CPDefinitionLocalServiceUtil.getCPDefinition(cpDefinitionId);

        data.put("pictureUrl", cpDefinition.getDefaultImageThumbnailSrc());

        CommerceContext commerceContext =
                (CommerceContext) request.getAttribute(
                        CommerceWebKeys.COMMERCE_CONTEXT);

        CommerceAccount commerceAccount =
                commerceContext.getCommerceAccount();

        long commerceAccountId = 0;

        if (commerceAccount != null) {
            commerceAccountId = commerceAccount.getCommerceAccountId();
        }

        HttpServletRequest originalHttpServletRequest =
                PortalUtil.getOriginalServletRequest(request);

        List<Long> cpDefinitionIds = CPCompareHelperUtil.getCPDefinitionIds(
                commerceContext.getCommerceChannelGroupId(), commerceAccountId,
                originalHttpServletRequest.getSession());

        data.put("inCompare", cpDefinitionIds.contains(cpDefinitionId));

        return data;
    }
}