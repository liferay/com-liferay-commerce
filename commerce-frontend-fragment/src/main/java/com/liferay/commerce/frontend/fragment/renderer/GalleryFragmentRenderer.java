package com.liferay.commerce.frontend.fragment.renderer;

import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.info.item.renderer.InfoItemRendererTracker;
import com.liferay.portal.kernel.language.LanguageUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

@Component(service = FragmentRenderer.class)
public class CommerceFrontendFragmentRenderer extends BaseCommerceFragmentRenderer {

    private static final String FRAGMENT_NAME = "Gallery";

    @Override
    protected String getFragmentName(Locale locale) {
        return LanguageUtil.get(locale, FRAGMENT_NAME);
    }

    @Override
    public void render(
            FragmentRendererContext fragmentRendererContext,
            HttpServletRequest request,
            HttpServletResponse response) {

        request.setAttribute(
                "fragmentRendererContext", fragmentRendererContext);

        InfoItemRenderer infoItemRenderer = _infoItemRendererTracker.getInfoItemRenderer(
                "com.liferay.commerce.frontend.taglib.internal.info.item.renderer.GalleryItemRenderer");
    }

    @Reference
    private InfoItemRendererTracker _infoItemRendererTracker;
}
